package com.example.demo.controller;

import com.example.demo.model.ServicePart;
import com.example.demo.service.ServicePartService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/service-parts")
public class ServicePartController {

    private final ServicePartService service;

    public ServicePartController(ServicePartService service) {
        this.service = service;
    }

    @PostMapping
    public ServicePart createServicePart(@Valid @RequestBody ServicePart part) {
        return service.createServicePart(part); // Ensure service method exists
    }

    @GetMapping("/{id}")
    public ServicePart getServicePartById(@PathVariable Long id) {
        return service.getServicePartById(id); // Ensure service method exists
    }

    @GetMapping("/entry/{entryId}")
    public List<ServicePart> getPartsByEntry(@PathVariable Long entryId) {
        return service.getPartsByEntry(entryId); // Ensure service method exists
    }
}
