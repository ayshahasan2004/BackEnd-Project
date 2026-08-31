package com.cafe.cafeBackend.service;

import com.cafe.cafeBackend.dto.MenuCategoryDto;
import com.cafe.cafeBackend.dto.MenuItemDto;
import com.cafe.cafeBackend.model.MenuCategory;
import com.cafe.cafeBackend.model.MenuItem;
import com.cafe.cafeBackend.repository.MenuCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor /// create arg constructor take the private menuCategoryRepository
public class MenuService {

    private final MenuCategoryRepository menuCategoryRepository;
    private final ItemMapper itemMapper;

    public List<MenuCategoryDto> getMenu() {
        List<MenuCategory> categories = menuCategoryRepository.findAll();
        List<MenuCategoryDto> result = new ArrayList<>();

        for (MenuCategory category : categories) {

            List<MenuItemDto> itemDtos = new ArrayList<>();
            for (MenuItem item : category.getItems()) {
                ItemResponse response = itemMapper.toResponse(item);
                itemDtos.add(itemDto);
            }

            MenuCategoryDto categoryDto = new MenuCategoryDto(
                    category.getName(),
                    itemDtos
            );

            result.add(categoryDto);
        }

        return result;
    }
}