package com.tungth.tuyensinh_be.dto;

import com.tungth.tuyensinh_be.entity.ApplicationStatus;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ApplicationResultResponse {
    private Long id;
    private String citizenId;
    private String fullName;
    private String certificate;
    private BigDecimal score;
    private ApplicationStatus status;

    public ApplicationResultResponse(Long id,String citizenId, String fullName, String certificate, BigDecimal score, ApplicationStatus status) {
        this.id = id;
        this.citizenId = citizenId;
        this.fullName = fullName;
        this.certificate = certificate;
        this.score = score;
        this.status = status;
    }

}
