package com.example.demo.service.impl;

import com.example.demo.model.Garage;
import com.example.demo.repository.GarageRepository;
import com.example.demo.service.GarageService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GarageServiceImpl implements GarageService {

    private final GarageRepository garageRepository;

    public GarageServiceImpl(GarageRepository garageRepository) {
        this.garageRepository = garageRepository;
    }

    @Override
    public Garage createGarage(Garage garage) {
        return garageRepository.save(garage);
    }

    @Override
    public Garage updateGarage(Long id, Garage garage) {
        Garage existing = garageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Garage not found"));

        existing.setGarageName(garage.getGarageName());
        existing.setAddress(garage.getAddress());
        existing.setActive(garage.getActive());

        return garageRepository.save(existing);
    }

    @Override
    public Garage getGarageById(Long id) {
        return garageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Garage not found"));
    }

    @Override
    public List<Garage> getAllGarages() {
        return garageRepository.findAll();
    }

    @Override
    public void deactivateGarage(Long id) {
        Garage garage = garageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Garage not found"));

        garage.setActive(false);
        garageRepository.save(garage);
    }
}
