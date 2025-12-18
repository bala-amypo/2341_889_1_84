 package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "verification_log")
public class VerificationLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "service_entry_id")
    private ServiceEntry serviceEntry;

    @Column(nullable = false, updatable = false)
    private LocalDateTime verifiedAt;

    @Column(nullable = false)
    private Boolean verifiedBySystem = true;

    private String notes;

    // 🔹 No-args constructor (required by JPA)
    public VerificationLog() {
        this.verifiedAt = LocalDateTime.now();
    }

    // 🔹 Parameterized constructor
    public VerificationLog(ServiceEntry serviceEntry, Boolean verifiedBySystem, String notes) {
        this.serviceEntry = serviceEntry;
        this.verifiedBySystem = verifiedBySystem;
        this.notes = notes;
        this.verifiedAt = LocalDateTime.now();
    }

    // 🔹 Getters and Setters

    public Long getId() {
        return id;
    }

    public ServiceEntry getServiceEntry() {
        return serviceEntry;
    }

    public void setServiceEntry(ServiceEntry serviceEntry) {
        this.serviceEntry = serviceEntry;
    }

    public LocalDateTime getVerifiedAt() {
        return verifiedAt;
    }

    public Boolean getVerifiedBySystem() {
        return verifiedBySystem;
    }

    public void setVerifiedBySystem(Boolean verifiedBySystem) {
        this.verifiedBySystem = verifiedBySystem;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
