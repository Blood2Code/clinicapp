package com.example.clinickapp.controller;

import com.example.clinickapp.dto.ResponseDto;
import com.example.clinickapp.dto.TreatmentsDto;
import com.example.clinickapp.service.TreatmentsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/treatments")
@RequiredArgsConstructor
@Api(tags = "Treatment Manager")
public class TreatmentController {
    private final TreatmentsService treatmentsService;
    @PostMapping("/create")
    @ApiOperation("Create treatment")
    public ResponseEntity<ResponseDto<TreatmentsDto>> createTreatment(@RequestBody TreatmentsDto treatmentsDto) {
        return  ResponseEntity.status(HttpStatus.OK).body(treatmentsService.createTreatment(treatmentsDto));
    }
    @GetMapping("/get-by-patient/{id}")
    @ApiOperation("Get treatment by patient id")
    public ResponseEntity<ResponseDto<List<TreatmentsDto>>> getByPatientId(@PathVariable Long id) {
        return  ResponseEntity.status(HttpStatus.OK).body(treatmentsService.getAllTreatmentsByPatientId(id));
    }
    @PutMapping("/update")
    @ApiOperation("Update treatment")
    public ResponseEntity<ResponseDto<TreatmentsDto>> updateTreatment(@RequestBody TreatmentsDto treatmentsDto) {
        return ResponseEntity.status(HttpStatus.OK).body(treatmentsService.updateTreatment(treatmentsDto));
    }
    @DeleteMapping("/{id}")
    @ApiOperation("Delete treatment")
    public ResponseEntity<ResponseDto<TreatmentsDto>> deleteTreatment(@PathVariable Long id) {
        treatmentsService.deleteTreatmentById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
