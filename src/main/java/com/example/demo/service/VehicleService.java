package com.example.demo.service;

import com.example.demo.model.Vehicle;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;

public interface VehicleService {
    Vehicle createVehicle(Vehicle vehicle);
    Vehicle getVehicleById(Long id) throws EntityNotFoundException;
    Vehicle getVehicleByVin(String vin) throws EntityNotFoundException;
    List<Vehicle> getVehiclesByOwner(Long ownerId);
    void deactivateVehicle(Long id) throws EntityNotFoundException;
}
