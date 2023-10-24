package com.example.clinickapp.service.mapper;

import com.example.clinickapp.dto.UserLoginDto;
import com.example.clinickapp.entity.UserLogin;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserLoginMapper {
    UserLogin toEntity(UserLoginDto userLoginDto);
    UserLoginDto toDto(UserLogin userLogin);
}
