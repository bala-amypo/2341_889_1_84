package com.example.demo.controller;

import com.example.demo.model.Garage;
import com.example.demo.service.GarageService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/garages")
public class GarageController {

    private final GarageService service;

    public GarageController(GarageService service) {
        this.service = service;
    }

    @PostMapping
    public Garage createGarage(@Valid @RequestBody Garage garage) {
        return service.createGarage(garage);
    }

    @PutMapping("/{id}")
    public Garage updateGarage(@PathVariable Long id, @Valid @RequestBody Garage garage) {
        return service.updateGarage(id, garage);
    }

    @GetMapping("/{id}")
    public Garage getGarageById(@PathVariable Long id) {
        return service.getGarageById(id);
    }

    @GetMapping
    public List<Garage> getAllGarages() {
        return service.getAllGarages();
    }

    @PutMapping("/{id}/deactivate")
    public void deactivateGarage(@PathVariable Long id) {
        service.deactivateGarage(id);  // return type is void
    }
}
