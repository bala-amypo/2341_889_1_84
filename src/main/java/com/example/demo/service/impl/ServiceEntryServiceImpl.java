// ServiceEntryServiceImpl.java
package com.example.demo.service.impl;

import com.example.demo.model.ServiceEntry;
import com.example.demo.repository.ServiceEntryRepository;
import com.example.demo.service.ServiceEntryService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceEntryServiceImpl implements ServiceEntryService {

    private final ServiceEntryRepository serviceEntryRepository;

    public ServiceEntryServiceImpl(
            ServiceEntryRepository serviceEntryRepository
    ) {
        this.serviceEntryRepository = serviceEntryRepository;
    }

    public ServiceEntry createServiceEntry(ServiceEntry serviceEntry) {
        return serviceEntryRepository.save(serviceEntry);
    }

    public List<ServiceEntry> getEntriesForVehicle(Long vehicleId) {
        return serviceEntryRepository.findByVehicleId(vehicleId);
    }
}
