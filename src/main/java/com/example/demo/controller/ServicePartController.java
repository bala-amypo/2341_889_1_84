package com.example.demo.controller;

import com.example.demo.model.ServicePart;
import com.example.demo.service.ServicePartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/service-parts")
public class ServicePartController {
    
    private final ServicePartService servicePartService;
    
    public ServicePartController(ServicePartService servicePartService) {
        this.servicePartService = servicePartService;
    }
    
    @PostMapping
    public ResponseEntity<ServicePart> createServicePart(@RequestBody ServicePart part) {
        ServicePart created = servicePartService.createPart(part);
        return ResponseEntity.ok(created);
    }
}