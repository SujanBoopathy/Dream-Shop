package com.sujan.tech.dream_shop.repository;

import com.sujan.tech.dream_shop.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart,Long>  {
    void deleteAllByCartId(Long id);
}
