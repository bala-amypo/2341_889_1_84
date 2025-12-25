package com.example.demo.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "garages")
public class Garage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String garageName;

    private String address;
    private Boolean active;

    @OneToMany(mappedBy = "garage")
    private List<ServiceEntry> serviceEntries;

    public Garage() {}

    public Garage(String garageName, String address, Boolean active) {
        this.garageName = garageName;
        this.address = address;
        this.active = active;
    }

    public Long getId() { 
    return id;
     }
    public String getGarageName() {
     return garageName; 
     }
    public void setGarageName(String garageName) { 
    this.garageName = garageName;
     }
    public String getAddress() {
     return address; 
     }
    public void setAddress(String address) { 
    this.address = address; 
    }
    public Boolean getActive() {
     return active; 
     }
    public void setActive(Boolean active) { 
    this.active = active; 
    }
}
