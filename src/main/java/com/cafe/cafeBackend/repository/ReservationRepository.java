package com.cafe.cafeBackend.repository;

import com.cafe.cafeBackend.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {}