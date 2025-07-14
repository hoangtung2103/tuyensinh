package com.tungth.tuyensinh_be.service;

import com.tungth.tuyensinh_be.dto.AdminLoginRequest;
import com.tungth.tuyensinh_be.dto.LoginResponse;
import com.tungth.tuyensinh_be.dto.StudentLoginRequest;
import com.tungth.tuyensinh_be.dto.StudentRegisterRequest;
import com.tungth.tuyensinh_be.entity.AdminAccount;
import com.tungth.tuyensinh_be.entity.StudentAccount;
import com.tungth.tuyensinh_be.repositoty.AdminAccountRepository;
import com.tungth.tuyensinh_be.repositoty.StudentAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private AdminAccountRepository adminAccountRepository;

    @Autowired
    private StudentAccountRepository studentAccountRepository;

    public LoginResponse adminLogin(AdminLoginRequest loginRequest) {
        AdminAccount admin = adminAccountRepository.findByUsername(loginRequest.getUsername())
                .orElse(null);
        if (admin == null || !loginRequest.getPassword().equals(admin.getPassword())) {
            return new LoginResponse(false, "Invalid username or password", null);
        }
        return new LoginResponse(true, "Login successful", admin);
    }

    public LoginResponse studentLogin(StudentLoginRequest loginRequest) {
        StudentAccount student = studentAccountRepository.findById(loginRequest.getCitizenId())
                .orElse(null);
        if (student == null || !loginRequest.getPassword().equals(student.getPassword())) {
            return new LoginResponse(false, "Invalid citizen ID or password", null);
        }
        return new LoginResponse(true, "Login successful", student);
    }

    public LoginResponse studentRegister(StudentRegisterRequest registerRequest) {
        // Kiểm tra citizenId đã tồn tại
        if (studentAccountRepository.existsById(registerRequest.getCitizenId())) {
            return new LoginResponse(false, "Citizen ID already exists", null);
        }
        // Kiểm tra email đã tồn tại
        if (studentAccountRepository.findAll().stream()
                .anyMatch(student -> student.getEmail().equals(registerRequest.getEmail()))) {
            return new LoginResponse(false, "Email already exists", null);
        }
        // Tạo tài khoản mới
        StudentAccount student = new StudentAccount();
        student.setCitizenId(registerRequest.getCitizenId());
        student.setFullName(registerRequest.getFullName());
        student.setPassword(registerRequest.getPassword()); // Plaintext
        student.setEmail(registerRequest.getEmail());
        studentAccountRepository.save(student);
        return new LoginResponse(true, "Registration successful", student);
    }
}