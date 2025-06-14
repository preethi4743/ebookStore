package com.bookstore.controller;

import com.bookstore.service.AdminStatsService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/admin/stats")
public class AdminStatsController {

    @Autowired
    private AdminStatsService statsService;

    @GetMapping
    public ResponseEntity<?> getStats(HttpServletRequest request) {
        Claims user = (Claims) request.getAttribute("adminUser");

        // 🔍 Log for safety
        System.out.println("Request attribute 'adminUser': " + user);

        if (user == null) {
            return ResponseEntity.status(401).body("Missing or invalid token");
        }

        String role = String.valueOf(user.get("role")).trim().toLowerCase();
        System.out.println("Extracted role: " + role);

        if (!"admin".equals(role)) {
            return ResponseEntity.status(403).body("Forbidden: Not an admin");
        }

        Map<String, Object> stats = statsService.getStats();
        return ResponseEntity.ok(stats);
    }
}
