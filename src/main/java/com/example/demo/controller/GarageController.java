package com.example.demo.controller;

import com.example.demo.model.Garage;
import com.example.demo.service.GarageService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/garages")
public class GarageController {

    private final GarageService garageService;

    public GarageController(GarageService garageService) {
        this.garageService = garageService;
    }

    @PostMapping
    public Garage createGarage(@RequestBody Garage garage) {
        return garageService.createGarage(garage);
    }

    @PutMapping("/{id}")
    public Garage updateGarage(@PathVariable Long id, @RequestBody Garage garage) {
        return garageService.updateGarage(id, garage);
    }

    @GetMapping("/{id}")
    public Garage getGarageById(@PathVariable Long id) {
        return garageService.getGarageById(id);
    }

    @GetMapping
    public List<Garage> getAllGarages() {
        return garageService.getAllGarages();
    }

    @PutMapping("/deactivate/{id}")
    public void deactivateGarage(@PathVariable Long id) {
        garageService.deactivateGarage(id);
    }
}
