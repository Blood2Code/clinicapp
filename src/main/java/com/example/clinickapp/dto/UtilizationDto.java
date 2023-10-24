package com.example.clinickapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UtilizationDto {
    private Long id;
    private String type;
    private String name;
    private Date usedDate;
    private UserLoginDto byWhom;
    private PatientDto patient;
    private Integer quantity;
}
