package com.tungth.tuyensinh_be.entity;

import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vladmihalcea.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Setter
@Getter
@Entity
@Table(name = "Applications")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Application {

    public enum ApplicationStatus {
        pending,
        approved,
        rejected
    }

    // Getters and Setters
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "citizenId", nullable = false)
    private StudentAccount studentAccount;

    @Column(nullable = false)
    private LocalDateTime submissionDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ApplicationStatus status;

    @Column(nullable = false, length = 255)
    private String addr;

    @Column(length = 255)
    private String certificate;

    @Column(precision = 10, scale = 2)
    private BigDecimal score;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "json")
    private Map<String, Object> additionalInfo;

    @Column(nullable = false)
    private Integer applicationYear;

}
