package com.tungth.tuyensinh_be.repositoty;

import com.tungth.tuyensinh_be.entity.StudentAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentAccountRepository extends JpaRepository<StudentAccount, String> {
}