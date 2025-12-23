// VerificationLog.java
package com.example.demo.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "verification_logs")
public class VerificationLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(
            name = "service_entry_id",
            nullable = false
    )
    private ServiceEntry serviceEntry;

    @CreationTimestamp
    private LocalDateTime verifiedAt;

    public VerificationLog() {
    }

    public VerificationLog(
            ServiceEntry serviceEntry
    ) {
        this.serviceEntry = serviceEntry;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getVerifiedAt() {
        return verifiedAt;
    }
}
