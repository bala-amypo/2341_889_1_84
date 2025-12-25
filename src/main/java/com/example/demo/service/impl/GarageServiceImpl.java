package com.example.demo.service.impl;

import com.example.demo.model.Garage;
import com.example.demo.repository.GarageRepository;
import com.example.demo.service.GarageService;
import jakarta.persistence.EntityNotFoundException;
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
        Garage existingGarage = garageRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Garage not found with id: " + id));

        existingGarage.setGarageName(garage.getGarageName());
        existingGarage.setAddress(garage.getAddress());
        existingGarage.setActive(garage.getActive());

        return garageRepository.save(existingGarage);
    }

    @Override
    public Garage getGarageById(Long id) {
        return garageRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Garage not found with id: " + id));
    }

    @Override
    public List<Garage> getAllGarages() {
        return garageRepository.findAll();
    }

    @Override
    public void deactivateGarage(Long id) {
        Garage garage = garageRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Garage not found with id: " + id));

        garage.setActive(false);
        garageRepository.save(garage);
    }
}
