package com.bookstore.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document(collection = "auth_tokens")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthToken {
    @Id
    private String id;

    private String username;
    private String token;
    private Instant issuedAt;
    private Instant expiresAt;
}
