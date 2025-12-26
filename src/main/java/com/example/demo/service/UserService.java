package com.example.demo.service;

import com.example.demo.model.User;

public interface UserService {

    User register(User user);                 // /auth/register

    User login(String email, String password); // /auth/login

    User getUserById(Long id);                // /users/{id}
}
