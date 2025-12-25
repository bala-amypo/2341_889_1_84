package com.example.demo.repository;

import com.example.demo.model.ServiceEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ServiceEntryRepository
        extends JpaRepository<ServiceEntry, Long> {

    List<ServiceEntry> findByVehicleId(Long vehicleId);

    List<ServiceEntry> findByGarageId(Long garageId);
}
