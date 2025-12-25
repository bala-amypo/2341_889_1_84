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
    private ServiceEntry serviceEntry;

    private LocalDateTime verifiedAt;

    public VerificationLog() {}

    public VerificationLog(ServiceEntry serviceEntry) {
        this.serviceEntry = serviceEntry;
        this.verifiedAt = LocalDateTime.now();
    }

    public Long getId() { 
        return id; 
        }
    public ServiceEntry getServiceEntry() {
         return serviceEntry;
          }
    public LocalDateTime getVerifiedAt() {
         return verifiedAt;
          }
}
