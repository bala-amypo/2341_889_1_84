package com.example.demo.repository;

import com.example.demo.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    Optional<Vehicle> findByVin(String vin);

    List<Vehicle> findByOwnerId(Long ownerId);
}


package com.example.demo.repository;

import com.example.demo.model.Garage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GarageRepository extends JpaRepository<Garage, Long> {

    Optional<Garage> findByGarageName(String garageName);
}

package com.example.demo.repository;

import com.example.demo.model.ServiceEntry;
import com.example.demo.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceEntryRepository extends JpaRepository<ServiceEntry, Long> {

    ServiceEntry findTopByVehicleOrderByOdometerReadingDesc(Vehicle vehicle);

    List<ServiceEntry> findByVehicleId(Long vehicleId);
}

package com.example.demo.repository;

import com.example.demo.model.ServicePart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServicePartRepository extends JpaRepository<ServicePart, Long> {

    List<ServicePart> findByServiceEntryId(Long serviceEntryId);
}

package com.example.demo.repository;

import com.example.demo.model.VerificationLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VerificationLogRepository extends JpaRepository<VerificationLog, Long> {

    List<VerificationLog> findByServiceEntryId(Long serviceEntryId);
}

package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}
