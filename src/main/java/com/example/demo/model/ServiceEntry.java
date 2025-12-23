// ServiceEntry.java
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
    @JoinColumn(name = "vehicle_id", nullable = false)
    private Vehicle vehicle;

    @ManyToOne
    @JoinColumn(name = "garage_id", nullable = false)
    private Garage garage;

    @Column(nullable = false)
    private String serviceType;

    @Column(nullable = false)
    private LocalDate serviceDate;

    @Column(nullable = false)
    private Integer odometerReading;

    @OneToMany(
            mappedBy = "serviceEntry",
            cascade = CascadeType.ALL
    )
    private List<ServicePart> serviceParts;

    public ServiceEntry() {
    }

    public ServiceEntry(
            Vehicle vehicle,
            Garage garage,
            String serviceType,
            LocalDate serviceDate,
            Integer odometerReading
    ) {
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

    public Integer getOdometerReading() {
        return odometerReading;
    }
}
