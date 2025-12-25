package com.example.demo.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "service_entries")
public class ServiceEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    @ManyToOne(optional = false)
    @JoinColumn(name = "garage_id")
    private Garage garage;

    @Column(nullable = false)
    private String description;

    @CreationTimestamp
    private LocalDateTime serviceDate;

    @Column(nullable = false)
    private Double cost;

    public ServiceEntry() {
    }

    public ServiceEntry(Vehicle vehicle, Garage garage, String description, Double cost) {
        this.vehicle = vehicle;
        this.garage = garage;
        this.description = description;
        this.cost = cost;
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

    public String getDescription() {
        return description;
    }

    public LocalDateTime getServiceDate() {
        return serviceDate;
    }

    public Double getCost() {
        return cost;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public void setGarage(Garage garage) {
        this.garage = garage;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }
}
