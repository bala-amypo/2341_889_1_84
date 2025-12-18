 package com.example.demo.model;

 public class User{
    @Id
    private Long id;
    @column(unique=true)
    private String email;
    private String password;
    private String role;
 }