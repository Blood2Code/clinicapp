package com.example.clinickapp.controller;

import com.example.clinickapp.dto.PatientDto;
import com.example.clinickapp.dto.ResponseDto;
import com.example.clinickapp.service.PatientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("api/patients")
@RequiredArgsConstructor
@Api(tags = "Patient Management")
public class PatientController {
    private final PatientService patientService;

    @GetMapping
    @ApiOperation(value = "View patient page")
    public String patientPage(Model model) {
        return "/patient";
    }

    @PostMapping("/create")
    @ApiOperation(value = "Create patient")
    public String createPatient(@ModelAttribute PatientDto patientDto) {
        ResponseDto<PatientDto> response = patientService.createPatient(patientDto);
        return "/patient";
    }
    @GetMapping("/all")
    @ApiOperation(value = "Get patients")
    public String getAllPatients(Model model) {
        model.addAttribute("patientList", patientService.getAllPatients());
        return "/patients";
    }
    @GetMapping("/{id}")
    @ApiOperation(value = "Get patient")
    public ResponseEntity<ResponseDto<PatientDto>> getPatientById(@PathVariable Long id, Model model) {
        model.addAttribute("asd", patientService.getPatientById(id));
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
