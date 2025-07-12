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
@Table(name = "StudentAccounts")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class StudentAccount {
    // Getters and Setters
    @Id
    @Column(length = 12)
    private String citizenId;

    @Column(nullable = false, length = 100)
    private String fullName;

    @Column(nullable = false, length = 255)
    private String password;

    @Column(nullable = false, length = 100, unique = true)
    private String email;

}