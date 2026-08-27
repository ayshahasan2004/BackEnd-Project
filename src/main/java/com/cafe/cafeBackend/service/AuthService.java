package com.cafe.cafeBackend.service;

import com.cafe.cafeBackend.dto.AuthResponse;
import com.cafe.cafeBackend.dto.LoginRequest;
import com.cafe.cafeBackend.dto.SignupRequest;
import com.cafe.cafeBackend.model.User;
import com.cafe.cafeBackend.model.User;
import com.cafe.cafeBackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthResponse signup(SignupRequest request) {
        if (userRepository.existsByEmail(request.email())) {
            throw new IllegalStateException("Email already registered");
        }

        User newUser = User.builder()
                .name(request.name())
                .phone(request.phone())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .build();

        userRepository.save(newUser);

        return new AuthResponse(String.valueOf(newUser.getId()), newUser.getEmail(), "temporary-fake-token");    }

    public AuthResponse login(LoginRequest request) {
        User existingUser = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new IllegalStateException("Invalid email or password"));

        if (!passwordEncoder.matches(request.password(), existingUser.getPassword())) {
            throw new IllegalStateException("Invalid email or password");
        }

        return new AuthResponse(String.valueOf(existingUser.getId()), existingUser.getEmail(), "temporary-fake-token");    }
}