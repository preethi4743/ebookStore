package com.bookstore.config;

import com.bookstore.security.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class WebSecurityConfig {

    @Autowired
    private JwtFilter jwtFilter;  // ✅ inject the filter instance

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/books", "/api/books/**").permitAll()
                        .requestMatchers("/api/orders", "/api/orders/**").permitAll()
                        .requestMatchers("/api/user/admin", "/api/user/**").permitAll()
                        .requestMatchers("/api/admin/stats").permitAll()
                        .requestMatchers("/api/books/create-book", "/api/books/edit/**", "/api/books/{id}").authenticated()
                )
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // ✅ Add the actual filter bean
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
