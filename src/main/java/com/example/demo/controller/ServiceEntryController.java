package com.example.demo.controller;

import com.example.demo.model.ServiceEntry;
import com.example.demo.service.ServiceEntryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/service-entries")
public class ServiceEntryController {
    
    private final ServiceEntryService serviceEntryService;
    
    public ServiceEntryController(ServiceEntryService serviceEntryService) {
        this.serviceEntryService = serviceEntryService;
    }
    
    @PostMapping
    public ResponseEntity<ServiceEntry> createServiceEntry(@RequestBody ServiceEntry entry) {
        ServiceEntry created = serviceEntryService.createServiceEntry(entry);
        return ResponseEntity.ok(created);
    }
    
    @GetMapping("/vehicle/{vehicleId}")
    public ResponseEntity<List<ServiceEntry>> getEntriesForVehicle(@PathVariable Long vehicleId) {
        List<ServiceEntry> entries = serviceEntryService.getEntriesForVehicle(vehicleId);
        return ResponseEntity.ok(entries);
    }
}