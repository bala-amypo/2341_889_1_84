package com.example.demo.service;

import com.example.demo.exception.EntityNotFoundException;
import com.example.demo.model.VerificationLog;
import com.example.demo.repository.VerificationLogRepository;
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
                .orElseThrow(() -> new EntityNotFoundException("Log not found"));
    }

    @Override
    public List<VerificationLog> getLogsForEntry(Long entryId) {
        return repository.findByServiceEntryId(entryId);
    }
}


