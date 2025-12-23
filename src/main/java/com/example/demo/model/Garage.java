// Garage.java
package com.example.demo.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(
        name = "garages",
        uniqueConstraints = @UniqueConstraint(columnNames = "garageName")
)
public class Garage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String garageName;

    @Column(nullable = false)
    private String address;

    private Boolean active;

    @OneToMany(
            mappedBy = "garage",
            cascade = CascadeType.ALL
    )
    private List<ServiceEntry> serviceEntries;

    public Garage() {
    }

    public Garage(
            String garageName,
            String address,
            Boolean active
    ) {
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

    public Boolean getActive() {
        return active;
    }
}
