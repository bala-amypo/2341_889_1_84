package com.example.demo.controller;

import com.example.demo.model.Vehicle;
import com.example.demo.service.VehicleService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

    private final VehicleService service;

    public VehicleController(VehicleService service) {
        this.service = service;
    }

    @PostMapping
    public Vehicle createVehicle(@Valid @RequestBody Vehicle vehicle) {
        return service.createVehicle(vehicle);
    }

    @GetMapping("/{id}")
    public Vehicle getVehicleById(@PathVariable Long id) {
        return service.getVehicleById(id);
    }

    @GetMapping("/vin/{vin}")
    public Vehicle getVehicleByVin(@PathVariable String vin) {
        return service.getVehicleByVin(vin);
    }

    @GetMapping("/owner/{ownerId}")
    public List<Vehicle> getVehiclesByOwner(@PathVariable Long ownerId) {
        return service.getVehiclesByOwner(ownerId);
    }

    @PutMapping("/{id}/deactivate")
    public Vehicle deactivateVehicle(@PathVariable Long id) {
        return service.deactivateVehicle(id);
    }
}
