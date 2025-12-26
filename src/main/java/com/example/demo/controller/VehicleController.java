package com.example.demo.controller;

import com.example.demo.model.Vehicle;
import com.example.demo.service.VehicleService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
@Tag(name = "Vehicle Management")
public class VehicleController {

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @PostMapping
    public ResponseEntity<Vehicle> registerVehicle(@RequestBody Vehicle vehicle) {
        return ResponseEntity.ok(vehicleService.createVehicle(vehicle));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vehicle> getVehicleById(@PathVariable Long id) {
        return ResponseEntity.ok(vehicleService.getVehicleById(id));
    }

    @GetMapping("/vin/{vin}")
    public ResponseEntity<Vehicle> getVehicleByVin(@PathVariable String vin) {
        return ResponseEntity.ok(vehicleService.getVehicleByVin(vin));
    }

    @GetMapping("/owner/{ownerId}")
    public ResponseEntity<List<Vehicle>> getVehiclesByOwner(@PathVariable Long ownerId) {
        return ResponseEntity.ok(vehicleService.getVehiclesByOwner(ownerId));
    }

    @PutMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivateVehicle(@PathVariable Long id) {
        vehicleService.deactivateVehicle(id);
        return ResponseEntity.noContent().build();
    }
}