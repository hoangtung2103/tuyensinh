package com.tungth.tuyensinh_be.dto;

public class LoginResponse {
    private boolean success;
    private String message;
    private Object user;

    public LoginResponse(boolean success, String message, Object user) {
        this.success = success;
        this.message = message;
        this.user = user;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getUser() {
        return user;
    }

    public void setUser(Object user) {
        this.user = user;
    }
}