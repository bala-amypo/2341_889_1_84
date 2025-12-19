package com.example.demo.service.impl;

import com.example.demo.exception.EntityNotFoundException;
import com.example.demo.model.ServiceEntry;
import com.example.demo.model.Vehicle;
import com.example.demo.repository.ServiceEntryRepository;
import com.example.demo.repository.VehicleRepository;
import com.example.demo.service.ServiceEntryService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ServiceEntryServiceImpl implements ServiceEntryService {

    private final ServiceEntryRepository serviceEntryRepository;
    private final VehicleRepository vehicleRepository;

    public ServiceEntryServiceImpl(ServiceEntryRepository serviceEntryRepository,
                                   VehicleRepository vehicleRepository) {
        this.serviceEntryRepository = serviceEntryRepository;
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public ServiceEntry createServiceEntry(ServiceEntry entry) {
        if (entry.getVehicle() == null || entry.getVehicle().getId() == null) {
            throw new IllegalArgumentException("Vehicle information is required");
        }

        Vehicle vehicle = vehicleRepository.findById(entry.getVehicle().getId())
                .orElseThrow(() -> new EntityNotFoundException("Vehicle not found"));

        if (!Boolean.TRUE.equals(vehicle.getActive())) {
            throw new IllegalArgumentException("Vehicle is not active");
        }

        if (entry.getServiceDate() == null || entry.getServiceDate().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Service date cannot be in the future");
        }

        ServiceEntry lastEntry = serviceEntryRepository
                .findTopByVehicleOrderByOdometerReadingDesc(vehicle);

        if (lastEntry != null && entry.getOdometerReading() < lastEntry.getOdometerReading()) {
            throw new IllegalArgumentException("Odometer reading cannot be less than the previous entry");
        }

        return serviceEntryRepository.save(entry);
    }

    @Override
    public ServiceEntry getServiceEntryById(Long id) {
        return serviceEntryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Service entry not found"));
    }

    @Override
    public List<ServiceEntry> getEntriesForVehicle(Long vehicleId) {
        return serviceEntryRepository.findByVehicleId(vehicleId);
    }

    @Override
    public List<ServiceEntry> getEntriesByGarage(Long garageId) {
        // Ideally, create a repository method: findByGarageId(garageId)
        return serviceEntryRepository.findAll()
                .stream()
                .filter(entry -> entry.getGarage() != null && entry.getGarage().getId().equals(garageId))
                .toList();
    }
}
