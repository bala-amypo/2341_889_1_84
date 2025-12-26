package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "service_parts")
public class ServicePart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "service_entry_id")
    private ServiceEntry serviceEntry;

    @Column(nullable = false)
    private String partName;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private Double price;

    public ServicePart() {
    }

    public ServicePart(
            ServiceEntry serviceEntry,
            String partName,
            Integer quantity,
            Double price
    ) {
        this.serviceEntry = serviceEntry;
        this.partName = partName;
        this.quantity = quantity;
        this.price = price;
    }

    /* ===== getters & setters required by tests ===== */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {   // ✅ REQUIRED BY TESTS
        this.id = id;
    }

    public ServiceEntry getServiceEntry() {
        return serviceEntry;
    }

    public void setServiceEntry(ServiceEntry serviceEntry) {
        this.serviceEntry = serviceEntry;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
