package com.example.demo.controller;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.model.User;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    
    public AuthController(JwtTokenProvider jwtTokenProvider, PasswordEncoder passwordEncoder, UserService userService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }
    
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        User user = userService.findByEmail(request.getEmail());
        if (user != null && passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            String token = jwtTokenProvider.generateToken(user.getEmail(), user.getRole(), user.getId());
            AuthResponse response = new AuthResponse(token, user.getId(), user.getEmail(), user.getRole());
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(401).build();
    }
    
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody AuthRequest request) {
        if (userService.findByEmail(request.getEmail()) != null) {
            return ResponseEntity.status(400).build();
        }
        User user = new User(request.getEmail(), request.getPassword(), "USER");
        User savedUser = userService.createUser(user);
        String token = jwtTokenProvider.generateToken(savedUser.getEmail(), savedUser.getRole(), savedUser.getId());
        AuthResponse response = new AuthResponse(token, savedUser.getId(), savedUser.getEmail(), savedUser.getRole());
        return ResponseEntity.ok(response);
    }
}