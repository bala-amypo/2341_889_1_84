package com.example.demo.controller;

import com.example.demo.model.VerificationLog;
import com.example.demo.service.VerificationLogService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/verification-logs")
public class VerificationLogController {

    private final VerificationLogService verificationLogService;

    public VerificationLogController(VerificationLogService verificationLogService) {
        this.verificationLogService = verificationLogService;
    }

    // POST /api/verification-logs
    @PostMapping
    public VerificationLog create(@RequestBody VerificationLog log) {
        return verificationLogService.createLog(log);
    }

    // GET /api/verification-logs/{id}
    @GetMapping("/{id}")
    public VerificationLog getById(@PathVariable Long id) {
        return verificationLogService.getLogById(id);
    }

    // GET /api/verification-logs/entry/{entryId}
    @GetMapping("/entry/{entryId}")
    public List<VerificationLog> getByEntry(@PathVariable Long entryId) {
        return verificationLogService.getLogsForEntry(entryId);
    }
}
