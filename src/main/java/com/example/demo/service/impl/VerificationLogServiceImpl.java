// VerificationLogServiceImpl.java
package com.example.demo.service.impl;

import com.example.demo.model.VerificationLog;
import com.example.demo.repository.VerificationLogRepository;
import com.example.demo.service.VerificationLogService;
import org.springframework.stereotype.Service;

@Service
public class VerificationLogServiceImpl implements VerificationLogService {

    private final VerificationLogRepository verificationLogRepository;

    public VerificationLogServiceImpl(
            VerificationLogRepository verificationLogRepository
    ) {
        this.verificationLogRepository = verificationLogRepository;
    }

    public VerificationLog createLog(VerificationLog verificationLog) {
        return verificationLogRepository.save(verificationLog);
    }
}
