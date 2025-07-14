package com.tungth.tuyensinh_be.controller;

import com.tungth.tuyensinh_be.dto.AdminLoginRequest;
import com.tungth.tuyensinh_be.dto.LoginResponse;
import com.tungth.tuyensinh_be.dto.StudentLoginRequest;
import com.tungth.tuyensinh_be.dto.StudentRegisterRequest;
import com.tungth.tuyensinh_be.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/admin/auth/login")
    public ResponseEntity<LoginResponse> adminLogin(@RequestBody AdminLoginRequest loginRequest) {
        LoginResponse response = authService.adminLogin(loginRequest);
        if (response.isSuccess()) {
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(401).body(response);
    }

    @PostMapping("/student/auth/login")
    public ResponseEntity<LoginResponse> studentLogin(@RequestBody StudentLoginRequest loginRequest) {
        LoginResponse response = authService.studentLogin(loginRequest);
        if (response.isSuccess()) {
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(401).body(response);
    }

    @PostMapping("/student/auth/register")
    public ResponseEntity<LoginResponse> studentRegister(@RequestBody StudentRegisterRequest registerRequest) {
        LoginResponse response = authService.studentRegister(registerRequest);
        if (response.isSuccess()) {
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(400).body(response);
    }
}
