package com.example.clinickapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PatientDto {
    private Long id;
    private String name;
    private String surname;
    private String gender;
    private Date birthDate;
    private String passportSerial;
    private Date receivedDate;
    private Integer wardNumber;
    private Integer wardBedNumber;
    private Date wardedDate;
    private String illness;
    private String diagnosis;
    private List<TreatmentsDto> treatments;
    private List<UtilizationDto> utilizationList;
}
