package com.cafe.cafeBackend.controller;

import com.cafe.cafeBackend.dto.OrderRequest;
import com.cafe.cafeBackend.dto.OrderResponse;
import com.cafe.cafeBackend.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public OrderResponse createOrder(@RequestBody OrderRequest request, Authentication authentication) {
        String userEmail = authentication.getName();
        return orderService.createOrder(request, userEmail);
    }
}