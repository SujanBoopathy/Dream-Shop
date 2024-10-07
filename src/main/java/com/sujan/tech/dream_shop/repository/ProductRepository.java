package com.sujan.tech.dream_shop.repository;

import com.sujan.tech.dream_shop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
