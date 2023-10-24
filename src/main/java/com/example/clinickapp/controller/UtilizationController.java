package com.example.clinickapp.controller;

import com.example.clinickapp.dto.ResponseDto;
import com.example.clinickapp.dto.UtilizationDto;
import com.example.clinickapp.service.UtilizationService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/utilization")
@RequiredArgsConstructor
@Api(tags = "Utilization Management")
public class UtilizationController {
    private final UtilizationService utilizationService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDto<UtilizationDto>> createUtilization(@RequestBody UtilizationDto utilizationDto) {
        ResponseDto<UtilizationDto> response = utilizationService.createUtilization(utilizationDto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDto<UtilizationDto>> updateUtilization(@RequestBody UtilizationDto utilizationDto) {
        ResponseDto<UtilizationDto> response = utilizationService.updateUtilization(utilizationDto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/patient/{id}")
    public ResponseEntity<ResponseDto<List<UtilizationDto>>> getAllUtilizationByPatientId(@PathVariable Long id) {
        ResponseDto<List<UtilizationDto>> response = utilizationService.getAllUtilizationByPatientId(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto<String>> deleteUtilizationById(@PathVariable Long id) {
        ResponseDto<String> response = utilizationService.deleteUtilizationById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
