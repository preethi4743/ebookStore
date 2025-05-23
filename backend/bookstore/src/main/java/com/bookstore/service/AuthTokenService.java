package com.bookstore.service;

import com.bookstore.model.AuthToken;
import com.bookstore.repository.AuthRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthTokenService {

    private final AuthRepository authRepository;

    public AuthTokenService(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    public AuthToken saveToken(AuthToken authToken) {
        return authRepository.save(authToken);
    }

    public Optional<AuthToken> findByToken(String token) {
        return authRepository.findByToken(token);
    }

    public void deleteByToken(String token) {
        authRepository.deleteByToken(token);
    }

    public boolean existsByToken(String token) {
        return authRepository.existsByToken(token);
    }
}
