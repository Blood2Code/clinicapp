package com.example.clinickapp.service.mapper;

import com.example.clinickapp.dto.PatientDto;
import com.example.clinickapp.dto.TreatmentsDto;
import com.example.clinickapp.entity.Patient;
import com.example.clinickapp.entity.Treatments;
import org.mapstruct.*;


@Mapper(componentModel = "spring")
public interface TreatmentsMapper {
    Treatments toEntity(TreatmentsDto treatmentsDto);
    @Mapping(target = "patient", source = "patient", qualifiedByName = "mapPatient")
    TreatmentsDto toDto(Treatments treatments);
    @Named("mapPatient")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "surname", source = "surname")
    @Mapping(target = "gender", source = "gender")
    @Mapping(target = "birthDate", source = "birthDate")
    @Mapping(target = "passportSerial", source = "passportSerial")
    @Mapping(target = "receivedDate", source = "receivedDate")
    @Mapping(target = "wardNumber", source = "wardNumber")
    @Mapping(target = "wardBedNumber", source = "wardBedNumber")
    @Mapping(target = "wardedDate", source = "wardedDate")
    @Mapping(target = "illness", source = "illness")
    @Mapping(target = "diagnosis", source = "diagnosis")
    PatientDto toPatientDto(Patient patient);
}
