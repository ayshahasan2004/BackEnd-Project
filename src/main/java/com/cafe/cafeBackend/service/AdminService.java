package com.cafe.cafeBackend.service;

import com.cafe.cafeBackend.model.Role;
import com.cafe.cafeBackend.model.User;
import com.cafe.cafeBackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class AdminService {
    private final UserRepository userRepository;
    public User promoteToAdmin(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalStateException("User not found"));
        //change CUSTOMER -> ADMIN
        user.setRole(Role.ADMIN);
        return userRepository.save(user);
    }
}