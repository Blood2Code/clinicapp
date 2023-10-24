package com.example.clinickapp.service.mapper;

import com.example.clinickapp.dto.PatientDto;
import com.example.clinickapp.dto.UtilizationDto;
import com.example.clinickapp.entity.Patient;
import com.example.clinickapp.entity.Utilization;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface UtilizationMapper {
    @Mapping(target = "patient", source = "patient", qualifiedByName = "mapPatient")
    UtilizationDto toDto(Utilization utilization);
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
    Utilization toEntity(UtilizationDto utilizationDto);
}
