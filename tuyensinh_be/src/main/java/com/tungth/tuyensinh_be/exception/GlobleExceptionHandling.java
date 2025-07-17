package com.tungth.tuyensinh_be.exception;

// Xử ly tập trung ngoại lệ, để khi gọi các API thì trả về thông điệp lỗi thay vì "Internal Server Error"

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobleExceptionHandling {
    @ExceptionHandler(value = RuntimeException.class)
    ResponseEntity<String> handleRuntime(RuntimeException exception){
        return ResponseEntity.badRequest().body(exception.getMessage());
    }
}
