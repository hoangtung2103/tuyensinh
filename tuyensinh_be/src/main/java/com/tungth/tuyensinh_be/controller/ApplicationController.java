package com.tungth.tuyensinh_be.controller;

import com.tungth.tuyensinh_be.dto.*;
import com.tungth.tuyensinh_be.entity.ApplicationStatus;
import com.tungth.tuyensinh_be.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/application")
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    @PostMapping("/submit")
    public ResponseEntity<LoginResponse> submitApplication(@RequestBody ApplicationSubmitRequest request) {
        LoginResponse response = applicationService.submitApplication(request);
        if (response.isSuccess()) {
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(400).body(response);
    }

    @GetMapping("/result")
    public ResponseEntity<?> getApplicationResult(@RequestParam String citizenId) {
        try {
            List<ApplicationResultResponse> results = applicationService.getApplicationResultByCitizenId(citizenId);
            return ResponseEntity.ok(results);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/list")
    public ResponseEntity<?> getAllApplications(
            @RequestParam(required = false) ApplicationStatus status,
            @RequestParam(required = false) Integer applicationYear,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            Page<ApplicationResultResponse> results = applicationService.getAllApplications(status, applicationYear, page, size);
            return ResponseEntity.ok(results);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<?> getApplicationDetail(@PathVariable Long id) {
        try {
            ApplicationDetailResponse result = applicationService.getApplicationDetailById(id);
            return ResponseEntity.ok(result);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping("/update-status/{id}")
    public ResponseEntity<?> updateApplicationStatus(@PathVariable Long id, @RequestBody UpdateStatusRequest request) {
        try {
            ApplicationDetailResponse result = applicationService.updateApplicationStatus(id, request);
            return ResponseEntity.ok(result);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", e.getMessage()));
        }
    }
}