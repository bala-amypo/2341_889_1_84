package com.example.demo.service;

import com.example.demo.model.User;

public interface UserService {
    User register(User user);            // For /auth/register
    User login(String email, String password);  // For /auth/login
    User getUserById(Long id);           // For /users/{id}
}

