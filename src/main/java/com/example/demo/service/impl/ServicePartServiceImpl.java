package com.example.demo.service.impl;

import com.example.demo.model.ServicePart;
import com.example.demo.model.ServiceEntry;
import com.example.demo.repository.ServicePartRepository;
import com.example.demo.repository.ServiceEntryRepository;
import com.example.demo.service.ServicePartService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ServicePartServiceImpl implements ServicePartService {
    
    private final ServicePartRepository servicePartRepository;
    private final ServiceEntryRepository serviceEntryRepository;
    
    public ServicePartServiceImpl(ServicePartRepository servicePartRepository,
                                ServiceEntryRepository serviceEntryRepository) {
        this.servicePartRepository = servicePartRepository;
        this.serviceEntryRepository = serviceEntryRepository;
    }
    
    @Override
    public ServicePart createPart(ServicePart part) {
        // Verify service entry exists
        ServiceEntry serviceEntry = serviceEntryRepository.findById(part.getServiceEntry().getId())
                .orElseThrow(() -> new EntityNotFoundException("Service entry not found"));
        
        // Validate quantity
        if (part.getQuantity() <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }
        
        part.setServiceEntry(serviceEntry);
        return servicePartRepository.save(part);
    }
}