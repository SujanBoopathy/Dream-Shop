package com.sujan.tech.dream_shop.repository;

import com.sujan.tech.dream_shop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {
    public List<Product> findByCategoryName(String category);
    public List<Product> findByBrand(String brand);
    public List<Product> findByCategoryNameAndBrand(String category,String brand);
    public List<Product> findByName(String productName);
    public List<Product> findByBrandAndName(String brand,String productName);
    public Long countByBrandAndName(String brand,String name);
}
