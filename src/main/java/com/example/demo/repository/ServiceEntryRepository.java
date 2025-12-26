package com.example.demo.repository;

import com.example.demo.model.ServiceEntry;
import com.example.demo.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ServiceEntryRepository extends JpaRepository<ServiceEntry, Long> {
    Optional<ServiceEntry> findTopByVehicleOrderByOdometerReadingDesc(Vehicle vehicle);
    List<ServiceEntry> findByVehicleId(Long vehicleId);
    
    @Query("SELECT s FROM ServiceEntry s WHERE s.garage.id = :garageId AND s.odometerReading > :minOdometer")
    List<ServiceEntry> findByGarageAndMinOdometer(@Param("garageId") Long garageId, @Param("minOdometer") int minOdometer);
    
    @Query("SELECT s FROM ServiceEntry s WHERE s.vehicle.id = :vehicleId AND s.serviceDate BETWEEN :from AND :to")
    List<ServiceEntry> findByVehicleAndDateRange(@Param("vehicleId") Long vehicleId, @Param("from") LocalDate from, @Param("to") LocalDate to);
}