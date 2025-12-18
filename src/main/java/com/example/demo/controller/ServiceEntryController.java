package com.example.demo.controller;

import com.example.demo.model.ServiceEntry;
import com.example.demo.service.ServiceEntryService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/service-entries")
public class ServiceEntryController {

    private final ServiceEntryService service;

    public ServiceEntryController(ServiceEntryService service) {
        this.service = service;
    }

    @PostMapping
    public ServiceEntry createServiceEntry(@Valid @RequestBody ServiceEntry entry) {
        return service.createServiceEntry(entry); // Make sure service method matches
    }

    @GetMapping("/{id}")
    public ServiceEntry getServiceEntryById(@PathVariable Long id) {
        return service.getServiceEntryById(id); // Make sure service method matches
    }

    @GetMapping("/vehicle/{vehicleId}")
    public List<ServiceEntry> getEntriesByVehicle(@PathVariable Long vehicleId) {
        return service.getEntriesByVehicle(vehicleId); // Must return List<ServiceEntry>
    }

    @GetMapping("/garage/{garageId}")
    public List<ServiceEntry> getEntriesByGarage(@PathVariable Long garageId) {
        return service.getEntriesByGarage(garageId); // Must return List<ServiceEntry>
    }
}
