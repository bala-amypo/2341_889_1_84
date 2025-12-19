package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.ServiceEntry;
import com.example.demo.repository.ServiceEntryRepository;
import com.example.demo.repository.VehicleRepository;
import com.example.demo.service.ServiceEntryService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

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

        if (entry.getServiceDate().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Service date cannot be in the future");
        }

        return serviceEntryRepository.save(entry);
    }

    @Override
    public ServiceEntry getServiceEntryById(Long id) {
        return serviceEntryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Service entry not found"));
    }
}
