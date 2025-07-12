package com.tungth.tuyensinh_be.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "Majors")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Major {
    // Getters and Setters
    @Id
    @Column(length = 10)
    private String majorCode;

    @Column(nullable = false, length = 100)
    private String majorName;

    @Column(nullable = false)
    private Integer quota;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String description;

}
