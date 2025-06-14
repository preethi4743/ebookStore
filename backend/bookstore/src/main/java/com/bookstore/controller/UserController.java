package com.bookstore.controller;

import com.bookstore.config.JwtUtil;
import com.bookstore.model.User;
import com.bookstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/admin")
    public ResponseEntity<?> loginAdmin(@RequestBody User loginRequest) {
        User admin = userRepo.findByUsername(loginRequest.getUsername());

        if (admin == null) {
            return ResponseEntity.status(404).body("Admin not found!");
        }

        if (!admin.getPassword().equals(loginRequest.getPassword())) {
            return ResponseEntity.status(401).body("Invalid password!");
        }

        String token = jwtUtil.generateToken(admin.getId(), admin.getUsername(), admin.getRole());

        return ResponseEntity.ok().body(
                java.util.Map.of(
                        "message", "Authentication successful",
                        "token", token,
                        "user", java.util.Map.of(
                                "username", admin.getUsername(),
                                "role", admin.getRole(),
                                "name", admin.getUsername()
                        )
                )
        );
    }
}
