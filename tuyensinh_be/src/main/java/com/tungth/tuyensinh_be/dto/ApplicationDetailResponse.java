package com.tungth.tuyensinh_be.dto;


import com.tungth.tuyensinh_be.entity.ApplicationStatus;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Setter
public class  ApplicationDetailResponse   {
    private Long id;
    private String citizenId;
    private String fullName;
    private LocalDateTime submissionDate;
    private ApplicationStatus status;
    private String addr;
    private String certificate;
    private BigDecimal score;
    private Map<String, Object> additionalInfo;
    private Integer applicationYear;

    public ApplicationDetailResponse(Long id, String citizenId, String fullName, LocalDateTime submissionDate,
                                     ApplicationStatus status, String addr, String certificate, BigDecimal score,
                                     Map<String, Object> additionalInfo, Integer applicationYear) {
        this.id = id;
        this.citizenId = citizenId;
        this.fullName = fullName;
        this.submissionDate = submissionDate;
        this.status = status;
        this.addr = addr;
        this.certificate = certificate;
        this.score = score;
        this.additionalInfo = additionalInfo;
        this.applicationYear = applicationYear;
    }

}
