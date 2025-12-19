package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Vehicle;
import com.example.demo.repository.VehicleRepository;
import com.example.demo.service.VehicleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;

    public VehicleServiceImpl(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public Vehicle createVehicle(Vehicle vehicle) {
        if (vehicleRepository.findByVin(vehicle.getVin()).isPresent()) {
            throw new IllegalArgumentException("VIN already exists");
        }
        return vehicleRepository.save(vehicle);
    }

    @Override
    public Vehicle getVehicleById(Long id) {
        return vehicleRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Vehicle not found"));
    }

    @Override
    public Vehicle getVehicleByVin(String vin) {
        return vehicleRepository.findByVin(vin)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Vehicle not found"));
    }

    @Override
    public List<Vehicle> getVehiclesByOwner(Long ownerId) {
        return vehicleRepository.findByOwnerId(ownerId);
    }

    @Override
    public void deactivateVehicle(Long id) {
        Vehicle vehicle = getVehicleById(id);
        vehicle.setActive(false);
        vehicleRepository.save(vehicle);
    }
}
