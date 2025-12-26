package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.VerificationLog;
import com.example.demo.repository.VerificationLogRepository;
import com.example.demo.service.VerificationLogService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VerificationLogServiceImpl implements VerificationLogService {

    private final VerificationLogRepository verificationLogRepository;

    public VerificationLogServiceImpl(VerificationLogRepository verificationLogRepository) {
        this.verificationLogRepository = verificationLogRepository;
    }

    @Override
    public VerificationLog createLog(VerificationLog verificationLog) {
        return verificationLogRepository.save(verificationLog);
    }

    @Override
    public VerificationLog getLogById(Long id) {
        return verificationLogRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Verification log not found with id: " + id));
    }

    @Override
    public List<VerificationLog> getLogsForEntry(Long serviceEntryId) {
        return verificationLogRepository.findByServiceEntryId(serviceEntryId);
    }
}
