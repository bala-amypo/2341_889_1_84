 package com.example.demo.model;

 import jakarta.persistence;

@Entity
@Table(
    name="garage"
)
 public class Gargage{
    @Id
    private Long id;
    private String gargageName;
    private String address;
    private String contactNumber;
    private Boolean active;
 }