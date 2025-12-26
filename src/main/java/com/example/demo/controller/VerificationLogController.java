package com.example.demo.controller;

import com.example.demo.model.VerificationLog;
import com.example.demo.service.VerificationLogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/verification-logs")
public class VerificationLogController {
    
    private final VerificationLogService verificationLogService;
    
    public VerificationLogController(VerificationLogService verificationLogService) {
        this.verificationLogService = verificationLogService;
    }
    
    @PostMapping
    public ResponseEntity<VerificationLog> createVerificationLog(@RequestBody VerificationLog log) {
        VerificationLog created = verificationLogService.createLog(log);
        return ResponseEntity.ok(created);
    }
}