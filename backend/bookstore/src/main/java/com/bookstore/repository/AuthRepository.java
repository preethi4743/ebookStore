package com.bookstore.repository;

import com.bookstore.model.AuthToken;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthRepository extends MongoRepository<AuthToken, String> {
    Optional<AuthToken> findByToken(String token);
    void deleteByToken(String token);
    boolean existsByToken(String token);
}
