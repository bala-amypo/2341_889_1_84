package com.example.demo.service.impl;

import com.example.demo.exception.EntityNotFoundException;
import com.example.demo.model.ServicePart;
import com.example.demo.repository.ServicePartRepository;
import com.example.demo.service.ServicePartService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicePartServiceImpl implements ServicePartService {

    private final ServicePartRepository repository;

    public ServicePartServiceImpl(ServicePartRepository repository) {
        this.repository = repository;
    }

    @Override
    public ServicePart createPart(ServicePart part) {
        if (part.getQuantity() <= 0) {
            throw new IllegalArgumentException("Quantity");
        }
        return repository.save(part);
    }

    @Override
    public ServicePart getPartById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Part not found"));
    }

    @Override
    public List<ServicePart> getPartsForEntry(Long entryId) {
        return repository.findByServiceEntryId(entryId);
    }
}
