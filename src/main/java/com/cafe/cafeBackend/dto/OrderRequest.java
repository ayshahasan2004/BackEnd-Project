package com.cafe.cafeBackend.dto;

import java.util.List;

public record OrderRequest(String time, String name, List<String> items, Double total) {}