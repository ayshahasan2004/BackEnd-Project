package com.cafe.cafeBackend.controller;

import com.cafe.cafeBackend.dto.ReservationRequest;
import com.cafe.cafeBackend.dto.ReservationResponse;
import com.cafe.cafeBackend.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping
    public ReservationResponse createReservation(@RequestBody ReservationRequest request, Authentication authentication) {
        String userEmail = authentication.getName();
        return reservationService.createReservation(request, userEmail);
    }
}