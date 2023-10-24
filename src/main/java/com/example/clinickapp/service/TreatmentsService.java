package com.example.clinickapp.service;

import com.example.clinickapp.dto.ResponseDto;
import com.example.clinickapp.dto.TreatmentsDto;
import com.example.clinickapp.entity.Treatments;
import com.example.clinickapp.repository.TreatmentsRepository;
import com.example.clinickapp.service.mapper.TreatmentsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TreatmentsService {
    private final TreatmentsRepository treatmentsRepository;
    private final TreatmentsMapper treatmentsMapper;

    public ResponseDto<TreatmentsDto> createTreatment(TreatmentsDto treatmentsDto) {
        try {
            Treatments treatment = treatmentsMapper.toEntity(treatmentsDto);
            Treatments savedTreatment = treatmentsRepository.save(treatment);

            return buildSuccessResponse(treatmentsMapper.toDto(savedTreatment));
        } catch (Exception ex) {
            return buildFailureResponse("Failed to create treatment: " + ex.getMessage());
        }
    }

    public ResponseDto<List<TreatmentsDto>> getAllTreatmentsByPatientId(Long id) {
        List<Treatments> treatmentsList = treatmentsRepository.findAllByPatient_Id(id);
        List<TreatmentsDto> treatmentsDtoList = treatmentsList.stream()
                .map(treatmentsMapper::toDto)
                .toList();

        return buildSuccessResponse(treatmentsDtoList);
    }

    public ResponseDto<TreatmentsDto> updateTreatment(TreatmentsDto treatmentsDto) {
        try {
            treatmentsRepository.save(treatmentsMapper.toEntity(treatmentsDto));
            return buildSuccessResponse(treatmentsDto);
        } catch (Exception ex) {
            return buildFailureResponse("Failed to update treatment: " + ex.getMessage());
        }
    }

    public ResponseDto<String> deleteTreatmentById(Long id) {
        try {
            treatmentsRepository.deleteById(id);
            return buildSuccessResponse("Treatment with ID " + id + " has been successfully deleted.");
        } catch (EmptyResultDataAccessException ex) {
            return buildFailureResponse("Treatment with ID " + id + " not found. Deletion failed.");
        } catch (Exception ex) {
            return buildFailureResponse("Failed to delete treatment with ID " + id + ": " + ex.getMessage());
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
