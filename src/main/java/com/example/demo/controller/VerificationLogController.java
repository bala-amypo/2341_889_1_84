package com.example.demo.controller;

import com.example.demo.model.VerificationLog;
import com.example.demo.service.VerificationLogService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/verification-logs")
public class VerificationLogController {

    private final VerificationLogService service;

    public VerificationLogController(VerificationLogService service) {
        this.service = service;
    }

    @PostMapping
    public VerificationLog create(@Valid @RequestBody VerificationLog log) {
        return service.createLog(log);
    }

    @GetMapping("/{id}")
    public VerificationLog getById(@PathVariable Long id) {
        return service.getLogById(id);
    }

    @GetMapping("/entry/{entryId}")
    public List<VerificationLog> getByEntry(@PathVariable Long entryId) {
        return service.getLogsByEntry(entryId);
    }
}
