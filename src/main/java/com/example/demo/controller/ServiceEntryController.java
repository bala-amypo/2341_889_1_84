// ServiceEntryController.java
package com.example.demo.controller;

import com.example.demo.model.ServiceEntry;
import com.example.demo.service.ServiceEntryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/service-entries")
public class ServiceEntryController {

    private final ServiceEntryService serviceEntryService;

    public ServiceEntryController(
            ServiceEntryService serviceEntryService
    ) {
        this.serviceEntryService = serviceEntryService;
    }

    @PostMapping
    public ServiceEntry createServiceEntry(
            @RequestBody ServiceEntry serviceEntry
    ) {
        return serviceEntryService.createServiceEntry(serviceEntry);
    }

    @GetMapping("/vehicle/{vehicleId}")
    public List<ServiceEntry> getEntriesByVehicle(
            @PathVariable Long vehicleId
    ) {
        return serviceEntryService.getEntriesForVehicle(vehicleId);
    }
}
