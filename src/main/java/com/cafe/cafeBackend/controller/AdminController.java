package com.cafe.cafeBackend.controller;

import com.cafe.cafeBackend.model.User;
import com.cafe.cafeBackend.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @PutMapping("/promote/{userId}")
    public ResponseEntity<User> promoteToAdmin(
            @PathVariable Long userId) {

        User user = adminService.promoteToAdmin(userId);

        return ResponseEntity.ok(user);
    }
}