package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "verification_logs")
public class VerificationLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "service_entry_id")
    private ServiceEntry serviceEntry;
    
    private LocalDateTime verifiedAt;
    
    public VerificationLog() {}
    
    public VerificationLog(ServiceEntry serviceEntry) {
        this.serviceEntry = serviceEntry;
    }
    
    public VerificationLog(ServiceEntry serviceEntry, LocalDateTime verifiedAt) {
        this.serviceEntry = serviceEntry;
        this.verifiedAt = verifiedAt;
    }
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public ServiceEntry getServiceEntry() { return serviceEntry; }
    public void setServiceEntry(ServiceEntry serviceEntry) { this.serviceEntry = serviceEntry; }
    
    public LocalDateTime getVerifiedAt() { return verifiedAt; }
    public void setVerifiedAt(LocalDateTime verifiedAt) { this.verifiedAt = verifiedAt; }
}