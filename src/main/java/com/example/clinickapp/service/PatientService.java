package com.example.clinickapp.service;

import com.example.clinickapp.dto.PatientDto;
import com.example.clinickapp.dto.ResponseDto;
import com.example.clinickapp.entity.Patient;
import com.example.clinickapp.repository.PatientRepository;
import com.example.clinickapp.service.mapper.PatientMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PatientService {
    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;

    public ResponseDto<PatientDto> createPatient(PatientDto patientDto) {
        try {
            Patient savedPatient = patientRepository.save(patientMapper.toEntity(patientDto));
            return buildSuccessResponse(patientMapper.toDto(savedPatient));
        } catch (Exception ex) {
            return buildFailureResponse("Failed to create patient: " + ex.getMessage());
        }
    }

    public ResponseDto<List<PatientDto>> getAllPatients() {
        try {
            List<PatientDto> patients = patientRepository.findAll()
                    .stream()
                    .map(patientMapper::toDto)
                    .collect(Collectors.toList());
            return buildSuccessResponse(patients);
        } catch (Exception ex) {
            return buildFailureResponse("Failed to retrieve patients: " + ex.getMessage());
        }
    }

    public ResponseDto<PatientDto> getPatientById(Long id) {
        try {
            Optional<Patient> patientOptional = patientRepository.findById(id);
            if (patientOptional.isPresent()) {
                return buildSuccessResponse(patientMapper.toDto(patientOptional.get()));
            } else {
                return buildNotFoundResponse("Patient not found");
            }
        } catch (Exception ex) {
            return buildFailureResponse("Failed to retrieve patient: " + ex.getMessage());
        }
    }

    public ResponseDto<String> deletePatientById(Long id) {
        try {
            if (patientRepository.existsById(id)) {
                patientRepository.deleteById(id);
                return buildSuccessResponse("Patient with ID " + id + " has been successfully deleted.");
            } else {
                return buildNotFoundResponse("Patient with ID " + id + " not found. Deletion failed.");
            }
        } catch (Exception ex) {
            return buildFailureResponse("Failed to delete patient with ID " + id + ": " + ex.getMessage());
        }
    }

    public ResponseDto<PatientDto> updatePatient(Long patientId, PatientDto updatedPatientDto) {
        try {
            Optional<Patient> existingPatientOptional = patientRepository.findById(patientId);
            if (existingPatientOptional.isPresent()) {
                Patient savedPatient = patientRepository.save(patientMapper.toEntity(updatedPatientDto));
                return buildSuccessResponse(patientMapper.toDto(savedPatient));
            } else {
                return buildNotFoundResponse("Patient not found");
            }
        } catch (Exception ex) {
            return buildFailureResponse("Failed to update patient: " + ex.getMessage());
        }
    }

    private <T> ResponseDto<T> buildSuccessResponse(T data) {
        return ResponseDto.<T>builder().code(0).data(data).build();
    }

    private <T> ResponseDto<T> buildFailureResponse(String message) {
        return ResponseDto.<T>builder().code(1).message(message).build();
    }

    private <T> ResponseDto<T> buildNotFoundResponse(String message) {
        return ResponseDto.<T>builder().code(1).message(message).build();
    }
}
