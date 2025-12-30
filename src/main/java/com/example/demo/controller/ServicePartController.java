package com.example.demo.controller;

import com.example.demo.model.ServicePart;
import com.example.demo.service.ServicePartService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/service-parts")
@Tag(name = "Service Part Management")
public class ServicePartController {

    private final ServicePartService servicePartService;

    public ServicePartController(ServicePartService servicePartService) {
        this.servicePartService = servicePartService;
    }

    @PostMapping
    public ResponseEntity<ServicePart> addPart(@RequestBody ServicePart part) {
        return ResponseEntity.ok(servicePartService.createPart(part));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServicePart> getPart(@PathVariable Long id) {
        return ResponseEntity.ok(servicePartService.getPartById(id));
    }

    @GetMapping("/entry/{entryId}")
    public ResponseEntity<List<ServicePart>> getPartsForEntry(@PathVariable Long entryId) {
        return ResponseEntity.ok(servicePartService.getPartsForEntry(entryId));
    }
}