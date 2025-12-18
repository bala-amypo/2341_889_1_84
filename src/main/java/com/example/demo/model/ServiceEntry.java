package  com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "service_entries")
public class ServiceEntry{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @manytoOne(optional = false)
    @joinColumn(name = "vehicle_id")
    private Vehicle vehicle;
    @manytoOne(optional = false)
    @joinColumn(name = "garge_id")
    private Gargage gargage;
    @Column(nullable=false)
    private String serviceType;
    @Column(nullable=false)
    private LocalDate serviceDate;
    @Column(nullable=false)
    private int odometerReading;
    private String description;
    @Column(nullable=false, updatable = false)
    private LocalDateTime recordedAt;

    @Prepersist
    protected void onCreate(){
        this.recordedAt = LocalDateTime.now();
    }

    public ServiceEntry(Long id, Vehicle vehicle, Gargage gargage, String serviceType, LocalDate serviceDate,
            int odometerReading, String description, LocalDateTime recordedAt) {
        this.id = id;
        this.vehicle = vehicle;
        this.gargage = gargage;
        this.serviceType = serviceType;
        this.serviceDate = serviceDate;
        this.odometerReading = odometerReading;
        this.description = description;
        this.recordedAt = recordedAt;
    }

    public ServiceEntry() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Gargage getGargage() {
        return gargage;
    }

    public void setGargage(Gargage gargage) {
        this.gargage = gargage;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public LocalDate getServiceDate() {
        return serviceDate;
    }

    public void setServiceDate(LocalDate serviceDate) {
        this.serviceDate = serviceDate;
    }

    public int getOdometerReading() {
        return odometerReading;
    }

    public void setOdometerReading(int odometerReading) {
        this.odometerReading = odometerReading;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getRecordedAt() {
        return recordedAt;
    }

    public void setRecordedAt(LocalDateTime recordedAt) {
        this.recordedAt = recordedAt;
    }
    
}