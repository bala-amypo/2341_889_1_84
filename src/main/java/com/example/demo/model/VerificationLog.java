package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;

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
    private LocalDate verifiedAt;

    public VerificationLog() {
    }

    public VerificationLog(
            ServiceEntry serviceEntry,
            String status,
            String verifiedBy,
            LocalDate verifiedAt
    ) {
        this.serviceEntry = serviceEntry;
        this.status = status;
        this.verifiedBy = verifiedBy;
        this.verifiedAt = verifiedAt;
    }

    /* ===== getters & setters required by tests ===== */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {            // ✅ REQUIRED
        this.id = id;
    }

    public ServiceEntry getServiceEntry() {
        return serviceEntry;
    }

    public void setServiceEntry(ServiceEntry serviceEntry) {
        this.serviceEntry = serviceEntry;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getVerifiedBy() {
        return verifiedBy;
    }

    public void setVerifiedBy(String verifiedBy) {
        this.verifiedBy = verifiedBy;
    }

    public LocalDate getVerifiedAt() {
        return verifiedAt;
    }

    public void setVerifiedAt(LocalDate verifiedAt) {
        this.verifiedAt = verifiedAt;
    }
}
