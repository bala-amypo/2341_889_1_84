package com.example.demo.serviceimpl;

import com.example.demo.exception.EntityNotFoundException;
import com.example.demo.model.ServiceEntry;
import com.example.demo.model.Vehicle;
import com.example.demo.repository.ServiceEntryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

        if (vehicle == null || !Boolean.TRUE.equals(vehicle.getActive())) {
            throw new IllegalArgumentException("Vehicle must be active");
        }

        if (entry.getServiceDate().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Service date cannot be in the future");
        }

        ServiceEntry last = repository.findTopByVehicleOrderByOdometerReadingDesc(vehicle);

        if (last != null && entry.getOdometerReading() < last.getOdometerReading()) {
            throw new IllegalArgumentException("Odometer reading cannot be less than last recorded reading");
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

