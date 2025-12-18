package com.example.demo.serviceimpl;

import com.example.demo.exception.EntityNotFoundException;
import com.example.demo.model.Garage;
import com.example.demo.repository.GarageRepository;
import com.example.demo.service.GarageService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GarageServiceImpl implements GarageService {

    private final GarageRepository repository;

    public GarageServiceImpl(GarageRepository repository) {
        this.repository = repository;
    }

    @Override
    public Garage createGarage(Garage garage) {
        return repository.save(garage);
    }

    @Override
    public Garage updateGarage(Long id, Garage garage) {
        Garage existing = getGarageById(id);
        existing.setGarageName(garage.getGarageName());
        existing.setAddress(garage.getAddress());
        existing.setContactNumber(garage.getContactNumber());
        existing.setActive(garage.getActive());
        return repository.save(existing);
    }

    @Override
    public Garage getGarageById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Garage not found"));
    }

    @Override
    public List<Garage> getAllGarages() {
        return repository.findAll();
    }

    @Override
    public Garage deactivateGarage(Long id) {
        Garage garage = getGarageById(id);
        garage.setActive(false);
        return repository.save(garage);
    }
}
