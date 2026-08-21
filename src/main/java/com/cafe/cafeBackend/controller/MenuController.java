package com.cafe.cafeBackend.controller;

import com.cafe.cafeBackend.dto.MenuCategoryDto;
import com.cafe.cafeBackend.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/menu")
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

    @GetMapping("/getmenu")
    public List<MenuCategoryDto> getMenu() {
        return menuService.getMenu();
    }
}