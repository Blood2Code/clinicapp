package com.example.clinickapp.dto;

import lombok.*;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponseDto {
    private String token;
    private Date expireDate;
    private String refreshToken;
}