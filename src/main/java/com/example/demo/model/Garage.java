package com.example.demo.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "garages")
public class Garage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String garageName;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private Boolean active = true;

    @OneToMany(mappedBy = "garage")
    private List<ServiceEntry> serviceEntries;

    public Garage() {
    }

    public Garage(String garageName, String address, Boolean active) {
        this.garageName = garageName;
        this.address = address;
        this.active = active;
    }

    /* ===== getters & setters required by tests ===== */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public boolean isActive() {
        return active != null && active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
