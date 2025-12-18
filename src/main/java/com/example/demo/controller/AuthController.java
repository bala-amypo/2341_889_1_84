package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public User register(@Valid @RequestBody User user) {
        return service.register(user);
    }

    @GetMapping("/login")
    public String login(
            @RequestParam String username,
            @RequestParam String password) {
        return service.login(username, password);
    }
}
