 package com.example.demo.model;

 import jakarta.persistence.*;
 import java.math.BigDecimal;

 @Entity
 @Table(name = "service_parts")
 public class ServicePart{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @manyToOne(optional = false)
    @JoinColumn(name = "service_entry_id")
    private ServiceEntry serviceEntry;

    @Column(nullable = false)
    private String partName;
    private String partNumbr;
    @Column(nullable = false)
    private BigDecimal cost;
    @Column(nullable = false)
    private int quantity;
    
    public ServicePart(Long id, ServiceEntry serviceEntry, String partName, String partNumbr, BigDecimal cost,
         int quantity) {
      this.id = id;
      this.serviceEntry = serviceEntry;
      this.partName = partName;
      this.partNumbr = partNumbr;
      this.cost = cost;
      this.quantity = quantity;
    }

    public ServicePart() {
    }

    public Long getId() {
       return id;
    }

    public void setId(Long id) {
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

    public String getPartNumbr() {
       return partNumbr;
    }

    public void setPartNumbr(String partNumbr) {
       this.partNumbr = partNumbr;
    }

    public BigDecimal getCost() {
       return cost;
    }

    public void setCost(BigDecimal cost) {
       this.cost = cost;
    }

    public int getQuantity() {
       return quantity;
    }

    public void setQuantity(int quantity) {
       this.quantity = quantity;
    }

 }