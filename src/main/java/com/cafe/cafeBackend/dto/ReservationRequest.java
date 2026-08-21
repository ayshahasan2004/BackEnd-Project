package com.cafe.cafeBackend.dto;

public record ReservationRequest(String date, String time, Integer party, String name, String phone) {}