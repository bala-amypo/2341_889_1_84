package com.example.demo.controller;

import com.example.demo.model.Garage;
import com.example.demo.service.GarageService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/garages")
@Tag(name = "Garage Management")
public class GarageController {

    private final GarageService garageService;

    public GarageController(GarageService garageService) {
        this.garageService = garageService;
    }

    @PostMapping
    public ResponseEntity<Garage> createGarage(@RequestBody Garage garage) {
        return ResponseEntity.ok(garageService.createGarage(garage));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Garage> updateGarage(@PathVariable Long id, @RequestBody Garage garage) {
        return ResponseEntity.ok(garageService.updateGarage(id, garage));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Garage> getGarageById(@PathVariable Long id) {
        return ResponseEntity.ok(garageService.getGarageById(id));
    }

    @GetMapping
    public ResponseEntity<List<Garage>> getAllGarages() {
        return ResponseEntity.ok(garageService.getAllGarages());
    }

    @PutMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivateGarage(@PathVariable Long id) {
        garageService.deactivateGarage(id);
        return ResponseEntity.noContent().build();
    }
}