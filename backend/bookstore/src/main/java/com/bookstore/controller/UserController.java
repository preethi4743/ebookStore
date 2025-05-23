package com.bookstore.controller;

import com.bookstore.model.User;
import com.bookstore.repository.UserRepository;
import com.bookstore.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class UserController {

    private final UserRepository userRepository;
    private final UserService userService;

    public UserController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }


    // Get user info by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable String id) {
        Optional<User> user = userService.findById(id);
        if (user.isPresent()) {
            User foundUser = user.get();
            // To avoid sending password in response, you can map to DTO or set password null
            foundUser.setPassword(null);
            return ResponseEntity.ok(foundUser);
        }
        return ResponseEntity.notFound().build();
    }
    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok(userRepository.findAll());
    }


    // Update user info (you may want to secure this endpoint!)
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable String id, @Valid @RequestBody User user) {
        Optional<User> existingUser = userService.findById(id);
        if (existingUser.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        User userToUpdate = existingUser.get();

        userToUpdate.setUsername(user.getUsername());
        userToUpdate.setEmail(user.getEmail());
        if(user.getPassword() != null && !user.getPassword().isEmpty()) {
            // encode new password
            userToUpdate.setPassword(user.getPassword());
        }
        userToUpdate.setRole(user.getRole());

        userService.saveUser(userToUpdate);
        userToUpdate.setPassword(null);
        return ResponseEntity.ok(userToUpdate);
    }

    // Delete user (admin-only typically)
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
