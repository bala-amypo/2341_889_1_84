// ServicePartController.java
package com.example.demo.controller;

import com.example.demo.model.ServicePart;
import com.example.demo.service.ServicePartService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/service-parts")
public class ServicePartController {

    private final ServicePartService servicePartService;

    public ServicePartController(
            ServicePartService servicePartService
    ) {
        this.servicePartService = servicePartService;
    }

    @PostMapping
    public ServicePart createServicePart(
            @RequestBody ServicePart servicePart
    ) {
        return servicePartService.createPart(servicePart);
    }
}
