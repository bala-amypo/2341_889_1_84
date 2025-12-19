package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.ServicePart;
import com.example.demo.model.ServiceEntry;
import com.example.demo.repository.ServicePartRepository;
import com.example.demo.repository.ServiceEntryRepository;
import com.example.demo.service.ServicePartService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicePartServiceImpl implements ServicePartService {

    private final ServicePartRepository partRepository;
    private final ServiceEntryRepository entryRepository;

    public ServicePartServiceImpl(ServicePartRepository partRepository,
                                  ServiceEntryRepository entryRepository) {
        this.partRepository = partRepository;
        this.entryRepository = entryRepository;
    }

    @Override
    public ServicePart createPart(Long entryId, ServicePart part) {
        // Check if the ServiceEntry exists
        ServiceEntry entry = entryRepository.findById(entryId)
                .orElseThrow(() -> new ResourceNotFoundException("Service entry not found"));

        if (part.getQuantity() <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }

        // Link the part to the entry
        part.setServiceEntry(entry);
        return partRepository.save(part);
    }

    @Override
    public ServicePart getPartById(Long id) {
        return partRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Service part not found"));
    }

    @Override
    public List<ServicePart> getPartsForEntry(Long entryId) {
        if (!entryRepository.existsById(entryId)) {
            throw new ResourceNotFoundException("Service entry not found");
        }
        return partRepository.findByServiceEntryId(entryId);
    }
}
