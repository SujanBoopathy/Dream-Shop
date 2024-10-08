package com.sujan.tech.dream_shop.repository;

import com.sujan.tech.dream_shop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {
    public List<Product> findByCategory(String category);
}
