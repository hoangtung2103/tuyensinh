package com.tungth.tuyensinh_be.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tungth.tuyensinh_be.dto.*;
import com.tungth.tuyensinh_be.entity.Application;
import com.tungth.tuyensinh_be.entity.ApplicationStatus;
import com.tungth.tuyensinh_be.entity.StudentAccount;
import com.tungth.tuyensinh_be.repositoty.ApplicationRepository;
import com.tungth.tuyensinh_be.repositoty.StudentAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private StudentAccountRepository studentAccountRepository;

    @Autowired
    private ObjectMapper objectMapper;

    public LoginResponse submitApplication(ApplicationSubmitRequest request) {
        // Kiểm tra citizenId tồn tại
        StudentAccount student = studentAccountRepository.findById(request.getCitizenId())
                .orElse(null);
        if (student == null) {
            return new LoginResponse(false, "Student with citizen ID not found", null);
        }

        // Tạo hồ sơ mới
        Application application = new Application();
        application.setStudentAccount(student);
        application.setSubmissionDate(request.getSubmissionDate());
        application.setStatus(ApplicationStatus.pending);
        application.setAddr(request.getAddr());
        application.setCertificate(request.getCertificate());
        application.setScore(request.getScore());
        application.setAdditionalInfo(request.getAdditionalInfo());
        application.setApplicationYear(request.getApplicationYear());

        // Lưu hồ sơ
        applicationRepository.save(application);
        return new LoginResponse(true, "Application submitted successfully", application);
    }

    public List<ApplicationResultResponse> getApplicationResultByCitizenId(String citizenId) {
        StudentAccount student = studentAccountRepository.findById(citizenId)
                .orElseThrow(() -> new RuntimeException("Student with citizen ID not found"));

        List<ApplicationResultResponse> results = applicationRepository.findAll().stream()
                .filter(app -> app.getStudentAccount().getCitizenId().equals(citizenId))
                .map(app -> new ApplicationResultResponse(
                        app.getId(),
                        app.getStudentAccount().getCitizenId(),
                        app.getStudentAccount().getFullName(),
                        app.getCertificate(),
                        app.getScore(),
                        app.getStatus()
                ))
                .collect(Collectors.toList());

        if (results.isEmpty()) {
            throw new RuntimeException("No applications found for this citizen ID");
        }

        return results;
    }

    public Page<ApplicationResultResponse> getAllApplications(ApplicationStatus status, Integer applicationYear, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Application> applications;

        if (status != null && applicationYear != null) {
            applications = applicationRepository.findByStatusAndApplicationYear(status, applicationYear, pageable);
        } else if (status != null) {
            applications = applicationRepository.findByStatus(status, pageable);
        } else if (applicationYear != null) {
            applications = applicationRepository.findByApplicationYear(applicationYear, pageable);
        } else {
            applications = applicationRepository.findAll(pageable);
        }

        List<ApplicationResultResponse> results = applications.getContent().stream()
                .map(app -> new ApplicationResultResponse(
                        app.getId(),
                        app.getStudentAccount().getCitizenId(),
                        app.getStudentAccount().getFullName(),
                        app.getCertificate(),
                        app.getScore(),
                        app.getStatus()
                ))
                .collect(Collectors.toList());

        return new PageImpl<>(results, pageable, applications.getTotalElements());
    }

    public ApplicationDetailResponse getApplicationDetailById(Long id) {
        Application application = applicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Application not found"));

        return new ApplicationDetailResponse(
                application.getId(),
                application.getStudentAccount().getCitizenId(),
                application.getStudentAccount().getFullName(),
                application.getSubmissionDate(),
                application.getStatus(),
                application.getAddr(),
                application.getCertificate(),
                application.getScore(),
                application.getAdditionalInfo(),
                application.getApplicationYear()
        );
    }

    public ApplicationDetailResponse updateApplicationStatus(Long id, UpdateStatusRequest request) {
        Application application = applicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Application not found"));

        ApplicationStatus newStatus = request.getStatus();
        if (newStatus != ApplicationStatus.approved && newStatus != ApplicationStatus.rejected) {
            throw new RuntimeException("Status must be APPROVED or REJECTED");
        }

        application.setStatus(newStatus);
        applicationRepository.save(application);

        return new ApplicationDetailResponse(
                application.getId(),
                application.getStudentAccount().getCitizenId(),
                application.getStudentAccount().getFullName(),
                application.getSubmissionDate(),
                application.getStatus(),
                application.getAddr(),
                application.getCertificate(),
                application.getScore(),
                application.getAdditionalInfo(),
                application.getApplicationYear()
        );
    }
}