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
    public ServiceEntry create(@Valid @RequestBody ServiceEntry entry) {
        return service.createServiceEntry(entry);
    }

    @GetMapping("/{id}")
    public ServiceEntry getById(@PathVariable Long id) {
        return service.getServiceEntryById(id);
    }

    @GetMapping("/vehicle/{vehicleId}")
    public List<ServiceEntry> getByVehicle(@PathVariable Long vehicleId) {
        return service.getEntriesForVehicle(vehicleId);
    }

    @GetMapping("/garage/{garageId}")
    public List<ServiceEntry> getByGarage(@PathVariable Long garageId) {
        return service.getEntriesByGarage(garageId);
    }
}
