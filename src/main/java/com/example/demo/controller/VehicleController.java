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
    public Vehicle create(@Valid @RequestBody Vehicle vehicle) {
        return service.createVehicle(vehicle);
    }


    @GetMapping("/{id}")
    public Vehicle getById(@PathVariable Long id) {
        return service.getVehicleById(id);
    }


    @GetMapping
    public List<Vehicle> getAll() {
        return service.getAllVehicles();
    }

    @PutMapping("/{id}")
    public Vehicle update(
            @PathVariable Long id,
            @Valid @RequestBody Vehicle vehicle) {
        return service.updateVehicle(id, vehicle);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteVehicle(id);
    }
}
