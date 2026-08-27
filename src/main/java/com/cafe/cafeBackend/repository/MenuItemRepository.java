package com.cafe.cafeBackend.repository;

import com.cafe.cafeBackend.model.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {}