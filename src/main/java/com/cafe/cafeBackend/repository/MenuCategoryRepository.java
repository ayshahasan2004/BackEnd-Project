package com.cafe.cafeBackend.repository;

import com.cafe.cafeBackend.model.MenuCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuCategoryRepository extends JpaRepository<MenuCategory, Long>  {}