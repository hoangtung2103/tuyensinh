package com.tungth.tuyensinh_be.dto;


import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Setter
public class ApplicationSubmitRequest {
    private String citizenId;
    private LocalDateTime submissionDate;
    private String addr;
    private String certificate;
    private BigDecimal score;
    private Map<String, Object> additionalInfo;
    private Integer applicationYear;

}
