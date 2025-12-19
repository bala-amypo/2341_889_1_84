package com.example.demo.controller;

import com.example.demo.model.Garage;
import com.example.demo.service.GarageService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/garages")
public class GarageController {

    private final GarageService garageService;

    public GarageController(GarageService garageService) {
        this.garageService = garageService;
    }

    // POST /api/garages
    @PostMapping
    public Garage createGarage(@RequestBody Garage garage) {
        return garageService.createGarage(garage);
    }

    // PUT /api/garages/{id}
    @PutMapping("/{id}")
    public Garage updateGarage(@PathVariable Long id, @RequestBody Garage garage) {
        return garageService.updateGarage(id, garage);
    }


    @GetMapping("/{id}")
    public Garage getGarage(@PathVariable Long id) {
        return garageService.getGarageById(id);
    }

    @GetMapping
    public List<Garage> getAll() {
        return garageService.getAllGarages();
    }

    @PutMapping("/{id}/deactivate")
    public void deactivate(@PathVariable Long id) {
        garageService.deactivateGarage(id);
    }
}
