package com.example.demo.repository;

import com.example.demo.model.VerificationLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VerificationLogRepository extends JpaRepository<VerificationLog, Long> {
    
}
