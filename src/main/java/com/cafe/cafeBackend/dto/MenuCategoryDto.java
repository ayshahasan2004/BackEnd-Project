package com.cafe.cafeBackend.dto;

import java.util.List;

public record MenuCategoryDto(String cat, List<MenuItemDto> items) {}