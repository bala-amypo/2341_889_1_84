// ServicePart.java
package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "service_parts")
public class ServicePart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(
            name = "service_entry_id",
            nullable = false
    )
    private ServiceEntry serviceEntry;

    @Column(nullable = false)
    private String partName;

    @Column(nullable = false)
    private Integer quantity;

    public ServicePart() {
    }

    public ServicePart(
            ServiceEntry serviceEntry,
            String partName,
            Integer quantity
    ) {
        this.serviceEntry = serviceEntry;
        this.partName = partName;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public Integer getQuantity() {
        return quantity;
    }
}
