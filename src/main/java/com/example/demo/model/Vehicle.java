// Vehicle.java
package com.example.demo.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(
        name = "vehicles",
        uniqueConstraints = @UniqueConstraint(columnNames = "vin")
)
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String vin;

    @Column(nullable = false)
    private String make;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private Long ownerId;

    private Boolean active;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @OneToMany(
            mappedBy = "vehicle",
            cascade = CascadeType.ALL
    )
    private List<ServiceEntry> serviceEntries;

    public Vehicle() {
    }

    public Vehicle(
            String vin,
            String make,
            String model,
            Long ownerId,
            Boolean active
    ) {
        this.vin = vin;
        this.make = make;
        this.model = model;
        this.ownerId = ownerId;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public String getVin() {
        return vin;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
