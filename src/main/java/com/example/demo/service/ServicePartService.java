package com.example.demo.service;

import com.example.demo.model.ServicePart;
import java.util.List;

public interface ServicePartService {
    ServicePart createPart(ServicePart part);
    List<ServicePart> getPartsForEntry(Long entryId);
    ServicePart getPartById(Long id);
}