package com.example.clinickapp.service.mapper;

import com.example.clinickapp.dto.PatientDto;
import com.example.clinickapp.dto.TreatmentsDto;
import com.example.clinickapp.dto.UtilizationDto;
import com.example.clinickapp.entity.Patient;
import com.example.clinickapp.entity.Treatments;
import com.example.clinickapp.entity.Utilization;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface PatientMapper {
    @Mapping(target = "treatments", source = "treatments", qualifiedByName = "mapTreatment")
    @Mapping(target = "utilizationList", source = "utilizationList", qualifiedByName = "mapUtilization")
    PatientDto toDto(Patient patient);

    @Named("mapTreatment")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "didDate", source = "didDate")
    @Mapping(target = "byWhom", source = "byWhom")
    TreatmentsDto toDtoTreatmentList(Treatments treatmentsList);

    @Named("mapUtilization")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "type", source = "type")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "usedDate", source = "usedDate")
    @Mapping(target = "byWhom", source = "byWhom")
    @Mapping(target = "quantity", source = "quantity")
    UtilizationDto toDtoUtilizationList(Utilization utilizationList);

    Patient toEntity(PatientDto patientDto);
}
