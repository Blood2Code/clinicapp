package com.example.clinickapp.controller;

import com.example.clinickapp.dto.ResponseDto;
import com.example.clinickapp.dto.UserLoginDto;
import com.example.clinickapp.service.UserLoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@Api(tags = "User Management")
@RequiredArgsConstructor
public class UserLoginController {
    private final UserLoginService userLoginService;
    @PostMapping("/create")
    @ApiOperation(value = "Create user")
    public ResponseEntity<ResponseDto<UserLoginDto>> createUser(@RequestBody UserLoginDto userLoginDto) {
        ResponseDto<UserLoginDto> response = userLoginService.createUser(userLoginDto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @PutMapping("/update")
    @ApiOperation(value = "Create user")
    public ResponseEntity<ResponseDto<UserLoginDto>> updateUser(@RequestBody UserLoginDto userLoginDto) {
        ResponseDto<UserLoginDto> response = userLoginService.updateUser(userLoginDto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @GetMapping("/all")
    @ApiOperation(value = "Get users")
    public ResponseEntity<ResponseDto<List<UserLoginDto>>> getAllUsers() {
        ResponseDto<List<UserLoginDto>> response = userLoginService.getAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get users")
    public ResponseEntity<ResponseDto<UserLoginDto>> getUserById(@PathVariable Long id) {
        ResponseDto<UserLoginDto> response = userLoginService.getUserById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete users")
    public ResponseEntity<ResponseDto> deleteUserById(@PathVariable Long id) {
        userLoginService.deleteUserById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
