package com.example.demo.service.impl;

import com.example.demo.model.VerificationLog;
import com.example.demo.repository.VerificationLogRepository;
import com.example.demo.service.VerificationLogService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VerificationLogServiceImpl implements VerificationLogService {

    private final VerificationLogRepository repository;

    public VerificationLogServiceImpl(VerificationLogRepository repository) {
        this.repository = repository;
    }

    @Override
    public VerificationLog createLog(VerificationLog log) {
        return repository.save(log);
    }

    @Override
    public VerificationLog getLogById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Verification log not found with id: " + id));
    }

    @Override
    public List<VerificationLog> getLogsByEntry(Long entryId) {
        return repository.findByServiceEntryId(entryId);
    }
}
