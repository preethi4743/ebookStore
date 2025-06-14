package com.bookstore.service;

import com.bookstore.model.User;
import com.bookstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User findByUsername(String username){
        return userRepository.findByUsername(username);

    }

    public boolean validatePassword(User user, String rawPassword) {
        return user.getPassword().equals(rawPassword);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }
}
