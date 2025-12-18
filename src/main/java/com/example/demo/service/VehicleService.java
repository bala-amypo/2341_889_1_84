 package com.example.demo.service;

import com.example.demo.model.Vehicle;
import com.example.demo.repository.VehicleRepository;
import com.example.demo.exception.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {

    private final VehicleRepository repository;
    
    public VehicleService(VehicleRepository repository) {
        this.repository = repository;
    }

    public Vehicle createVehicle(Vehicle vehicle) {
        if (repository.findByVin(vehicle.getVin()).isPresent()) {
            throw new IllegalArgumentException("VIN");
        }
        return repository.save(vehicle);
    }

    public Vehicle getVehicleById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Vehicle not found"));
    }
    public List<Vehicle> getAllVehicles() {
        return repository.findAll();
    }

    public Vehicle updateVehicle(Long id, Vehicle updatedVehicle) {
        Vehicle existing = getVehicleById(id);

        existing.setMake(updatedVehicle.getMake());
        existing.setModel(updatedVehicle.getModel());
        existing.setYear(updatedVehicle.getYear());
        existing.setOwnerId(updatedVehicle.getOwnerId());
        existing.setActive(updatedVehicle.getActive());

        return repository.save(existing);
    }
    public void deleteVehicle(Long id) {
        Vehicle vehicle = getVehicleById(id);
        repository.delete(vehicle);
    }
}
