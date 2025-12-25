package com.example.demo.repository;

import com.example.demo.model.ServicePart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServicePartRepository extends JpaRepository<ServicePart, Long> {

    List<ServicePart> findByServiceEntryId(Long serviceEntryId);
}
