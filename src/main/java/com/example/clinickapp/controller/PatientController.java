package com.example.clinickapp.controller;

import com.example.clinickapp.dto.PatientDto;
import com.example.clinickapp.dto.ResponseDto;
import com.example.clinickapp.service.PatientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/patients")
@RequiredArgsConstructor
@Api(tags = "Patient Management")
public class PatientController {
    private final PatientService patientService;
    @PostMapping("/create")
    @ApiOperation(value = "Create patient")
    public ResponseEntity<ResponseDto<PatientDto>> createPatient(@RequestBody PatientDto patientDto) {
        ResponseDto<PatientDto> response = patientService.createPatient(patientDto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @GetMapping("/all")
    @ApiOperation(value = "Get patients")
    public ResponseEntity<ResponseDto<List<PatientDto>>> getAllPatients() {
        return ResponseEntity.status(HttpStatus.OK).body(patientService.getAllPatients());
    }
    @GetMapping("/{id}")
    @ApiOperation(value = "Get patient")
    public ResponseEntity<ResponseDto<PatientDto>> getPatientById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(patientService.getPatientById(id));
    }
    @PutMapping("/update")
    @ApiOperation(value = "Update patient")
    public ResponseEntity<ResponseDto<PatientDto>> updatePatient(@RequestBody PatientDto patientDto) {
        return ResponseEntity.status(HttpStatus.OK).body(patientService.updatePatient(patientDto.getId(), patientDto));
    }
    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete patient")
    public ResponseEntity<ResponseDto<String>> deletePatientById(@PathVariable Long id) {
        patientService.deletePatientById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
