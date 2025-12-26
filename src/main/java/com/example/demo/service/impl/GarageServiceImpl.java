package com.example.demo.service.impl;

import com.example.demo.model.Garage;
import com.example.demo.repository.GarageRepository;
import com.example.demo.service.GarageService;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class GarageServiceImpl implements GarageService {
    
    private final GarageRepository garageRepository;
    
    public GarageServiceImpl(GarageRepository garageRepository) {
        this.garageRepository = garageRepository;
    }
    
    @Override
    public Garage createGarage(Garage garage) {
        Optional<Garage> existing = garageRepository.findByGarageName(garage.getGarageName());
        if (existing.isPresent()) {
            throw new IllegalArgumentException("Garage already exists with name: " + garage.getGarageName());
        }
        return garageRepository.save(garage);
    }
}