package com.example.demo.service;

import com.example.demo.exception.EntityNotFoundException;
import com.example.demo.model.ServiceEntry;
import com.example.demo.model.Vehicle;
import com.example.demo.repository.ServiceEntryRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ServiceEntryServiceImpl implements ServiceEntryService {

    private final ServiceEntryRepository repository;

    public ServiceEntryServiceImpl(ServiceEntryRepository repository) {
        this.repository = repository;
    }

    @Override
    public ServiceEntry createServiceEntry(ServiceEntry entry) {

        Vehicle vehicle = entry.getVehicle();

        if (!vehicle.getActive()) {
            throw new IllegalArgumentException("active vehicles");
        }

        if (entry.getServiceDate().after(new Date())) {
            throw new IllegalArgumentException("future");
        }

        ServiceEntry last = repository
                .findTopByVehicleOrderByOdometerReadingDesc(vehicle);

        if (last != null &&
            entry.getOdometerReading() < last.getOdometerReading()) {
            throw new IllegalArgumentException(">=");
        }

        return repository.save(entry);
    }

    @Override
    public ServiceEntry getServiceEntryById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Service entry not found"));
    }

    @Override
    public List<ServiceEntry> getEntriesForVehicle(Long vehicleId) {
        return repository.findByVehicleId(vehicleId);
    }

    @Override
    public List<ServiceEntry> getEntriesByGarage(Long garageId) {
        return repository.findAll()
                .stream()
                .filter(e -> e.getGarage().getId().equals(garageId))
                .toList();
    }
}
