 package com.example.demo.model;

 import jakarta.persistence;

 public class Gargage{
    @Id
    @Table()
    private Long id;
    private String gargageName;
    private String address;
    private String contactNumber;
    private Boolean active;
 }