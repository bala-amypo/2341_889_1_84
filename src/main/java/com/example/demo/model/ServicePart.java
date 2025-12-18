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
    private ServiceEntry serviceEntry
 }