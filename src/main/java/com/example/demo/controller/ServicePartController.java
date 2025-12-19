package com.example.demo.controller;

import com.example.demo.model.ServicePart;
import com.example.demo.service.ServicePartService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/service-parts")
public class ServicePartController {

    private final ServicePartService servicePartService;

    public ServicePartController(ServicePartService servicePartService) {
        this.servicePartService = servicePartService;
    }

    // POST /api/service-parts
    @PostMapping
    public ServicePart createPart(@RequestBody ServicePart part) {
        return servicePartService.createPart(part);
    }

    // GET /api/service-parts/{id}
    @GetMapping("/{id}")
    public ServicePart getById(@PathVariable Long id) {
        return servicePartService.getPartById(id);
    }

    // GET /api/service-parts/entry/{entryId}
    @GetMapping("/entry/{entryId}")
    public List<ServicePart> getByEntry(@PathVariable Long entryId) {
        return servicePartService.getPartsForEntry(entryId);
    }
}
