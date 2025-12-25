package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "service_entries")
public class ServiceEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Vehicle vehicle;

    @ManyToOne
    private Garage garage;

    private String serviceType;
    private LocalDate serviceDate;
    private Integer odometerReading;

    @OneToMany(mappedBy = "serviceEntry")
    private List<ServicePart> parts;

    public ServiceEntry() {}

    public ServiceEntry(Vehicle vehicle, Garage garage,
                        String serviceType, LocalDate serviceDate,
                        Integer odometerReading) {
        this.vehicle = vehicle;
        this.garage = garage;
        this.serviceType = serviceType;
        this.serviceDate = serviceDate;
        this.odometerReading = odometerReading;
    }

    public Long getId() { 
    return id; 
    }
    public Vehicle getVehicle() { 
    return vehicle; 
    }
    public Garage getGarage() {
     return garage;
      }
    public String getServiceType() { 
    return serviceType; 
    }
    public LocalDate getServiceDate() {
     return serviceDate;
      }
    public Integer getOdometerReading() {
     return odometerReading;
      }
}
