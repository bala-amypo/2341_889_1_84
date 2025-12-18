package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // 🔹 Register a new user
    @PostMapping("/register")
    public ResponseEntity<User> register(@Valid @RequestBody User user) {
        User savedUser = authService.register(user);
        return ResponseEntity.ok(savedUser);
    }

    // 🔹 Login
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        String result = authService.login(user.getEmail(), user.getPassword());
        return ResponseEntity.ok(result);
    }
}
