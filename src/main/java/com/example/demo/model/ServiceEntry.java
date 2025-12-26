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
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;
    
    @ManyToOne
    @JoinColumn(name = "garage_id")
    private Garage garage;
    
    private String serviceType;
    private LocalDate serviceDate;
    private Integer odometerReading;
    
    @OneToMany(mappedBy = "serviceEntry", cascade = CascadeType.ALL)
    private List<ServicePart> serviceParts;
    
    @OneToMany(mappedBy = "serviceEntry", cascade = CascadeType.ALL)
    private List<VerificationLog> verificationLogs;
    
    public ServiceEntry() {}
    
    public ServiceEntry(Vehicle vehicle, Garage garage, String serviceType, LocalDate serviceDate, Integer odometerReading) {
        this.vehicle = vehicle;
        this.garage = garage;
        this.serviceType = serviceType;
        this.serviceDate = serviceDate;
        this.odometerReading = odometerReading;
    }
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Vehicle getVehicle() { return vehicle; }
    public void setVehicle(Vehicle vehicle) { this.vehicle = vehicle; }
    
    public Garage getGarage() { return garage; }
    public void setGarage(Garage garage) { this.garage = garage; }
    
    public String getServiceType() { return serviceType; }
    public void setServiceType(String serviceType) { this.serviceType = serviceType; }
    
    public LocalDate getServiceDate() { return serviceDate; }
    public void setServiceDate(LocalDate serviceDate) { this.serviceDate = serviceDate; }
    
    public Integer getOdometerReading() { return odometerReading; }
    public void setOdometerReading(Integer odometerReading) { this.odometerReading = odometerReading; }
    
    public List<ServicePart> getServiceParts() { return serviceParts; }
    public void setServiceParts(List<ServicePart> serviceParts) { this.serviceParts = serviceParts; }
    
    public List<VerificationLog> getVerificationLogs() { return verificationLogs; }
    public void setVerificationLogs(List<VerificationLog> verificationLogs) { this.verificationLogs = verificationLogs; }
}