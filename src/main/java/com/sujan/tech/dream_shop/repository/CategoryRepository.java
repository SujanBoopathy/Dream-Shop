package com.sujan.tech.dream_shop.repository;

import com.sujan.tech.dream_shop.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    public Category findByName(String name);
    public boolean existsByName(String name);
}
