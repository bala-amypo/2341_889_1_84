package com.example.demo.service.impl;

import com.example.demo.model.ServicePart;
import com.example.demo.repository.ServicePartRepository;
import com.example.demo.service.ServicePartService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicePartServiceImpl implements ServicePartService {

    private final ServicePartRepository servicePartRepository;

    public ServicePartServiceImpl(ServicePartRepository servicePartRepository) {
        this.servicePartRepository = servicePartRepository;
    }

    @Override
    public ServicePart createPart(ServicePart servicePart) {
        return servicePartRepository.save(servicePart);
    }

    @Override
    public ServicePart getPartById(Long id) {
        return servicePartRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                "Service part not found with id: " + id));
    }

    @Override
    public List<ServicePart> getPartsForEntry(Long serviceEntryId) {
        return servicePartRepository.findByServiceEntryId(serviceEntryId);
    }
}
