package com.cafe.cafeBackend.repository;

import com.cafe.cafeBackend.model.user;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<user, String> {
    Optional<user> findByEmail(String email);
    boolean existsByEmail(String email);
}