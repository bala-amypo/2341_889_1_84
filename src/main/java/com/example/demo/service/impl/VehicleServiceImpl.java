 package com.example.demo.service.impl;

import com.example.demo.model.Vehicle;
import com.example.demo.repository.VehicleRepository;
import com.example.demo.service.VehicleService;
import com.example.demo.exception.EntityNotFoundException;
import org.springframework.stereotype.Service;



import java.util.List;

@Service
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository repository;

    public VehicleServiceImpl(VehicleRepository repository) {
        this.repository = repository;
    }

    @Override
    public Vehicle createVehicle(Vehicle vehicle) {

        // Business validation
        if (repository.findByVin(vehicle.getVin()).isPresent()) {
            throw new IllegalArgumentException("VIN already exists");
        }

        return repository.save(vehicle);
    }

    @Override
    public Vehicle getVehicleById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Vehicle not found"));
    }

    @Override
    public List<Vehicle> getAllVehicles() {
        return repository.findAll();
    }

    @Override
    public Vehicle updateVehicle(Long id, Vehicle updatedVehicle) {

        Vehicle existing = getVehicleById(id);

        existing.setMake(updatedVehicle.getMake());
        existing.setModel(updatedVehicle.getModel());
        existing.setYear(updatedVehicle.getYear());
        existing.setOwnerId(updatedVehicle.getOwnerId());
        existing.setActive(updatedVehicle.getActive());

        return repository.save(existing);
    }

    @Override
    public void deleteVehicle(Long id) {
        Vehicle vehicle = getVehicleById(id);
        repository.delete(vehicle);
    }
}
