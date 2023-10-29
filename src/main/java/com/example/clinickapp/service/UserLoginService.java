package com.example.clinickapp.service;

import com.example.clinickapp.dto.LoginDto;
import com.example.clinickapp.dto.ResponseDto;
import com.example.clinickapp.dto.UserLoginDto;
import com.example.clinickapp.entity.UserLogin;
import com.example.clinickapp.repository.UserLoginRepository;

import com.example.clinickapp.service.mapper.UserLoginMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserLoginService implements UserDetailsService {
    private final UserLoginRepository userLoginRepository;
    private final UserLoginMapper userLoginMapper;
    public static Map<Long, UserLoginDto> userMap = new HashMap<>();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserLogin userLogin = userLoginRepository.findFirstByName(username).get();
        return userLoginMapper.toDto(userLogin);
    }

    public ResponseDto<LoginDto> login(LoginDto loginDto) {
        try {
            UserLogin userLogin = userLoginRepository.findFirstByName(loginDto.getUsername()).orElseThrow(
                    () -> new UsernameNotFoundException(String.format("User with username %s not found", loginDto.getUsername()))
            );

            if (!userLogin.getPassword().equals(loginDto.getPassword())) {
                throw new BadCredentialsException("Password is incorrect");
            }
            userMap.put(userLogin.getId(), userLoginMapper.toDto(userLogin));

            return buildSuccessResponse(loginDto);
        } catch (Exception ex) {
            return buildFailureResponse("Failed to find user: " + ex.getMessage());
        }
    }


    public ResponseDto<UserLoginDto> createUser(UserLoginDto userLoginDto) {
        try {
            UserLogin userLogin = userLoginMapper.toEntity(userLoginDto);
            UserLogin savedUser = userLoginRepository.save(userLogin);

            return buildSuccessResponse(userLoginMapper.toDto(savedUser));
        } catch (Exception ex) {
            return buildFailureResponse("Failed to create user: " + ex.getMessage());
        }
    }

    public ResponseDto<UserLoginDto> updateUser(UserLoginDto userLoginDto) {
        try {
            UserLogin existingUser = userLoginRepository.findById(userLoginDto.getId()).orElse(null);

            if (existingUser == null) {
                return buildFailureResponse("User not found for updating");
            }

            userLoginMapper.toEntity(userLoginDto);

            UserLogin updatedUser = userLoginRepository.save(existingUser);

            return buildSuccessResponse(userLoginMapper.toDto(updatedUser));
        } catch (Exception ex) {
            return buildFailureResponse("Failed to update user: " + ex.getMessage());
        }
    }


    public ResponseDto<List<UserLoginDto>> getAllUsers() {
        List<UserLoginDto> userLoginDtoList = userLoginRepository.findAll().stream()
                .map(userLoginMapper::toDto)
                .toList();

        return buildSuccessResponse(userLoginDtoList);
    }

    public ResponseDto<UserLoginDto> getUserById(Long id) {
        Optional<UserLogin> userLoginOptional = userLoginRepository.findById(id);

        if (userLoginOptional.isPresent()) {
            return buildSuccessResponse(userLoginMapper.toDto(userLoginOptional.get()));
        } else {
            return buildFailureResponse("User with ID " + id + " not found.");
        }
    }

    public ResponseDto<String> deleteUserById(Long id) {
        try {
            userLoginRepository.deleteById(id);
            return buildSuccessResponse("User with ID " + id + " has been successfully deleted.");
        } catch (Exception ex) {
            return buildFailureResponse("Failed to delete user with ID " + id + ": " + ex.getMessage());
        }
    }

    private <T> ResponseDto<T> buildSuccessResponse(T data) {
        return ResponseDto.<T>builder()
                .code(0)
                .success(true)
                .data(data)
                .build();
    }

    private <T> ResponseDto<T> buildFailureResponse(String message) {
        return ResponseDto.<T>builder()
                .code(1)
                .message(message)
                .build();
    }
}
