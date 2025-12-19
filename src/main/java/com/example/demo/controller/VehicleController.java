package com.example.demo.controller;

import com.example.demo.model.Vehicle;
import com.example.demo.service.VehicleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    // POST /api/vehicles
    @PostMapping
    public Vehicle createVehicle(@RequestBody Vehicle vehicle) {
        return vehicleService.createVehicle(vehicle);
    }

    // GET /api/vehicles/{id}
    @GetMapping("/{id}")
    public Vehicle getById(@PathVariable Long id) {
        return vehicleService.getVehicleById(id);
    }

    // GET /api/vehicles/vin/{vin}
    @GetMapping("/vin/{vin}")
    public Vehicle getByVin(@PathVariable String vin) {
        return vehicleService.getVehicleByVin(vin);
    }

    // GET /api/vehicles/owner/{ownerId}
    @GetMapping("/owner/{ownerId}")
    public List<Vehicle> getByOwner(@PathVariable Long ownerId) {
        return vehicleService.getVehiclesByOwner(ownerId);
    }

    // PUT /api/vehicles/{id}/deactivate
    @PutMapping("/{id}/deactivate")
    public void deactivate(@PathVariable Long id) {
        vehicleService.deactivateVehicle(id);
    }
}
