package com.example.demo.service.impl;

import com.example.demo.model.ServiceEntry;
import com.example.demo.model.Vehicle;
import com.example.demo.model.Garage;
import com.example.demo.repository.ServiceEntryRepository;
import com.example.demo.repository.VehicleRepository;
import com.example.demo.repository.GarageRepository;
import com.example.demo.service.ServiceEntryService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceEntryServiceImpl implements ServiceEntryService {
    
    private final ServiceEntryRepository serviceEntryRepository;
    private final VehicleRepository vehicleRepository;
    private final GarageRepository garageRepository;
    
    public ServiceEntryServiceImpl(ServiceEntryRepository serviceEntryRepository, 
                                 VehicleRepository vehicleRepository,
                                 GarageRepository garageRepository) {
        this.serviceEntryRepository = serviceEntryRepository;
        this.vehicleRepository = vehicleRepository;
        this.garageRepository = garageRepository;
    }
    
    @Override
    public ServiceEntry createServiceEntry(ServiceEntry entry) {
        // Load and validate vehicle
        Vehicle vehicle = vehicleRepository.findById(entry.getVehicle().getId())
                .orElseThrow(() -> new EntityNotFoundException("Vehicle not found"));
        
        // Load and validate garage
        Garage garage = garageRepository.findById(entry.getGarage().getId())
                .orElseThrow(() -> new EntityNotFoundException("Garage not found"));
        
        // Check if vehicle is active
        if (!vehicle.getActive()) {
            throw new IllegalArgumentException("Service entries can only be created for active vehicles");
        }
        
        // Check future date
        if (entry.getServiceDate().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Service date cannot be in the future");
        }
        
        // Check odometer reading constraint
        Optional<ServiceEntry> lastEntry = serviceEntryRepository.findTopByVehicleOrderByOdometerReadingDesc(vehicle);
        if (lastEntry.isPresent() && entry.getOdometerReading() < lastEntry.get().getOdometerReading()) {
            throw new IllegalArgumentException("Odometer reading must be >= previous reading");
        }
        
        entry.setVehicle(vehicle);
        entry.setGarage(garage);
        return serviceEntryRepository.save(entry);
    }
    
    @Override
    public List<ServiceEntry> getEntriesForVehicle(Long vehicleId) {
        return serviceEntryRepository.findByVehicleId(vehicleId);
    }
}