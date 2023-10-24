
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
public class TreatmentsDto {
    private Long id;
    private String name;
    private Date didDate;
    private UserLoginDto byWhom;
    private PatientDto patient;
}
