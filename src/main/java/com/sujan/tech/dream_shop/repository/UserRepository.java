package com.sujan.tech.dream_shop.repository;


import com.sujan.tech.dream_shop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
}