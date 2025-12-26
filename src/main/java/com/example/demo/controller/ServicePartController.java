package com.example.demo.controller;

import com.example.demo.model.ServicePart;
import com.example.demo.service.ServicePartService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/service-parts")
@Tag(name = "Service Part API")
public class ServicePartController {

    private final ServicePartService servicePartService;

    public ServicePartController(ServicePartService servicePartService) {
        this.servicePartService = servicePartService;
    }

    @PostMapping
    public ServicePart createServicePart(@RequestBody ServicePart part) {
        return servicePartService.createPart(part);
    }

    @GetMapping("/{id}")
    public ServicePart getServicePartById(@PathVariable Long id) {
        return servicePartService.getPartById(id);
    }

    @GetMapping("/service-entry/{serviceEntryId}")
    public List<ServicePart> getPartsForServiceEntry(
            @PathVariable Long serviceEntryId
    ) {
        return servicePartService.getPartsForEntry(serviceEntryId);
    }
}
