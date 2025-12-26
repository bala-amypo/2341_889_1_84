package com.example.demo.controller;

import com.example.demo.model.ServiceEntry;
import com.example.demo.service.ServiceEntryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/service-entries")
@Tag(name = "Service Entry Management")
public class ServiceEntryController {

    private final ServiceEntryService serviceEntryService;

    public ServiceEntryController(ServiceEntryService serviceEntryService) {
        this.serviceEntryService = serviceEntryService;
    }

    @PostMapping
    public ResponseEntity<ServiceEntry> createServiceEntry(@RequestBody ServiceEntry entry) {
        return ResponseEntity.ok(serviceEntryService.createServiceEntry(entry));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceEntry> getEntryById(@PathVariable Long id) {
        return ResponseEntity.ok(serviceEntryService.getServiceEntryById(id));
    }

    @GetMapping("/vehicle/{vehicleId}")
    public ResponseEntity<List<ServiceEntry>> getEntriesForVehicle(@PathVariable Long vehicleId) {
        return ResponseEntity.ok(serviceEntryService.getEntriesForVehicle(vehicleId));
    }

    @GetMapping("/garage/{garageId}")
    public ResponseEntity<List<ServiceEntry>> getEntriesByGarage(@PathVariable Long garageId) {
        return ResponseEntity.ok(serviceEntryService.getEntriesByGarage(garageId));
    }
}