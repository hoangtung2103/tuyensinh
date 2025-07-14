package com.tungth.tuyensinh_be.repositoty;

import com.tungth.tuyensinh_be.entity.AdminAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AdminAccountRepository extends JpaRepository<AdminAccount, Long> {
    Optional<AdminAccount> findByUsername(String username);
}