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
    public ServicePart createPart(@Valid @RequestBody ServicePart part) {
        return service.createPart(part);
    }

    @GetMapping("/{id}")
    public ServicePart getById(@PathVariable Long id) {
        return service.getPartById(id);
    }

    @GetMapping("/entry/{entryId}")
    public List<ServicePart> getByEntry(@PathVariable Long entryId) {
        return service.getPartsForEntry(entryId);
    }
}
