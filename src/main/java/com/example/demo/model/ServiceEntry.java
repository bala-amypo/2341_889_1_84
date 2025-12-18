package  com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "service_entries")
public class ServiceEntry{
    private Long id;
    private Vehicle vehicle;
    private Gargage gargage;
    private String serviceType;
    private LocalDate serviceDate;
    private int odometerReading;
    
}
