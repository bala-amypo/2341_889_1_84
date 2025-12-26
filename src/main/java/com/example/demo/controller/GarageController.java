package com.example.demo.controller;

import com.example.demo.model.Garage;
import com.example.demo.service.GarageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/garages")
public class GarageController {
    
    private final GarageService garageService;
    
    public GarageController(GarageService garageService) {
        this.garageService = garageService;
    }
    
    @PostMapping
    public ResponseEntity<Garage> createGarage(@RequestBody Garage garage) {
        Garage created = garageService.createGarage(garage);
        return ResponseEntity.ok(created);
    }
}