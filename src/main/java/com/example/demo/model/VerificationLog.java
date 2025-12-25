package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "verification_logs")
public class VerificationLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "service_entry_id")
    private ServiceEntry serviceEntry;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private String verifiedBy;

    @Column(nullable = false)
    private LocalDateTime verifiedAt;

    public VerificationLog() {
    }

    public VerificationLog(ServiceEntry serviceEntry, String status, String verifiedBy, LocalDateTime verifiedAt) {
        this.serviceEntry = serviceEntry;
        this.status = status;
        this.verifiedBy = verifiedBy;
        this.verifiedAt = verifiedAt;
    }

    public Long getId() {
        return id;
    }

    public ServiceEntry getServiceEntry() {
        return serviceEntry;
    }

    public String getStatus() {
        return status;
    }

    public String getVerifiedBy() {
        return verifiedBy;
    }

    public LocalDateTime getVerifiedAt() {
        return verifiedAt;
    }

    public void setServiceEntry(ServiceEntry serviceEntry) {
        this.serviceEntry = serviceEntry;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setVerifiedBy(String verifiedBy) {
        this.verifiedBy = verifiedBy;
    }

    public void setVerifiedAt(LocalDateTime verifiedAt) {
        this.verifiedAt = verifiedAt;
    }
}
