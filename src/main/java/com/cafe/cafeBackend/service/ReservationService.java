package com.cafe.cafeBackend.service;

import com.cafe.cafeBackend.dto.ReservationRequest;
import com.cafe.cafeBackend.dto.ReservationResponse;
import com.cafe.cafeBackend.model.Reservation;
import com.cafe.cafeBackend.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.cafe.cafeBackend.aop.LogExecutionTime;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    @LogExecutionTime
    public ReservationResponse createReservation(ReservationRequest request, String userEmail) {
        Reservation reservation = Reservation.builder()
                .date(LocalDate.parse(request.date()))
                .time(request.time())
                .party(request.party())
                .name(request.name())
                .phone(request.phone())
                .userEmail(userEmail)
                .confirmedAt(LocalDateTime.now())
                .build();

        reservationRepository.save(reservation);

        return new ReservationResponse(String.valueOf(reservation.getId()), reservation.getConfirmedAt().toString());
    }
}