package com.example.clinickapp.service;

import com.example.clinickapp.dto.ResponseDto;
import com.example.clinickapp.dto.UtilizationDto;
import com.example.clinickapp.entity.Utilization;
import com.example.clinickapp.repository.UtilizationRepository;
import com.example.clinickapp.service.mapper.UtilizationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UtilizationService {
    private final UtilizationRepository utilizationRepository;
    private final UtilizationMapper utilizationMapper;

    public ResponseDto<UtilizationDto> createUtilization(UtilizationDto utilizationDto) {
        try {
            Utilization utilization = utilizationMapper.toEntity(utilizationDto);
            Utilization savedUtilization = utilizationRepository.save(utilization);

            return buildSuccessResponse(utilizationMapper.toDto(savedUtilization));
        } catch (Exception ex) {
            return buildFailureResponse("Failed to create utilization record: " + ex.getMessage());
        }
    }

    public ResponseDto<UtilizationDto> updateUtilization(UtilizationDto utilizationDto) {
        try {
            Utilization utilization = utilizationMapper.toEntity(utilizationDto);
            Utilization savedUtilization = utilizationRepository.save(utilization);

            return buildSuccessResponse(utilizationMapper.toDto(savedUtilization));
        } catch (Exception ex) {
            return buildFailureResponse("Failed to update utilization record: " + ex.getMessage());
        }
    }

    public ResponseDto<List<UtilizationDto>> getAllUtilizationByPatientId(Long id) {
        List<Utilization> utilizationList = utilizationRepository.findAllByPatient_Id(id);
        List<UtilizationDto> utilizationDtoList = utilizationList.stream()
                .map(utilizationMapper::toDto)
                .toList();

        return buildSuccessResponse(utilizationDtoList);
    }

    public ResponseDto<String> deleteUtilizationById(Long id) {
        try {
            Optional<Utilization> utilizationOptional = utilizationRepository.findById(id);

            if (utilizationOptional.isPresent()) {
                utilizationRepository.deleteById(id);
                return buildSuccessResponse("Utilization record with ID " + id + " has been successfully deleted.");
            } else {
                return buildFailureResponse("Utilization record with ID " + id + " not found. Deletion failed.");
            }
        } catch (Exception ex) {
            return buildFailureResponse("Failed to delete utilization record with ID " + id + ": " + ex.getMessage());
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
