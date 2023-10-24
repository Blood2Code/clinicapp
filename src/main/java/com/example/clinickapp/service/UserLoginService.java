package com.example.clinickapp.service;

import com.example.clinickapp.dto.ResponseDto;
import com.example.clinickapp.dto.UserLoginDto;
import com.example.clinickapp.entity.UserLogin;
import com.example.clinickapp.repository.UserLoginRepository;
import com.example.clinickapp.service.mapper.UserLoginMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserLoginService {
    private final UserLoginRepository userLoginRepository;
    private final UserLoginMapper userLoginMapper;

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

    public ResponseDto deleteUserById(Long id) {
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
