package  com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "service_entries")
public class ServiceEntry{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @manytoOne(optional = false)
    @joinColumn(name = "vehicle_id")
    private Vehicle vehicle;
    @manytoOne(optional = false)
    @joinColumn(name = "garge_id")
    private Gargage gargage;
    @Column(nullable=false)
    private String serviceType;
    @Column(nullable=false)
    private LocalDate serviceDate;
    @Column(nullable=false)
    private int odometerReading;
    private String description;
    @Column(nullable=false, updatable = false)
    private LocalDateTime recordedAt;

    @Prepersist
    protected void onCreate(){
        this.recordedAt = Local
    }
}
