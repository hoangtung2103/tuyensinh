package com.tungth.tuyensinh_be.dto;

public class StudentLoginRequest {
    private String citizenId;
    private String password;

    public String getCitizenId() {
        return citizenId;
    }

    public void setCitizenId(String citizenId) {
        this.citizenId = citizenId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}