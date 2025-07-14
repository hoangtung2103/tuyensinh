package com.tungth.tuyensinh_be.repositoty;

import com.tungth.tuyensinh_be.entity.Application;
import com.tungth.tuyensinh_be.entity.ApplicationStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    Page<Application> findByStatusAndApplicationYear(ApplicationStatus status, Integer applicationYear, Pageable pageable);
    Page<Application> findByStatus(ApplicationStatus status, Pageable pageable);
    Page<Application> findByApplicationYear(Integer applicationYear, Pageable pageable);
}