package com.sujan.tech.dream_shop.repository;

import com.sujan.tech.dream_shop.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartIteamRepository extends JpaRepository<CartItem,Long> {
}
