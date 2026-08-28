package com.cafe.cafeBackend.controller;

import com.cafe.cafeBackend.model.User;
import com.cafe.cafeBackend.repository.UserRepository;
import com.cafe.cafeBackend.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;
    private final UserRepository userRepository;

    // Search user by email
    @GetMapping("/users")
    public User getUserByEmail(@RequestParam String email) {

        return userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new IllegalStateException("User not found")
                );
    }
    // Promote CUSTOMER to ADMIN
    @PutMapping("/users/{userId}/promote")
    public User promoteToAdmin(@PathVariable Long userId) {

        return adminService.promoteToAdmin(userId);
    }
}