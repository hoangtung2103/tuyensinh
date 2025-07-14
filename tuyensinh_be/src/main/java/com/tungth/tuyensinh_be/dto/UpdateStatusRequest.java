package com.tungth.tuyensinh_be.dto;

import com.tungth.tuyensinh_be.entity.ApplicationStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateStatusRequest {
    private ApplicationStatus status;
}