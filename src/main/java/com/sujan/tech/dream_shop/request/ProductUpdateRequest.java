package com.sujan.tech.dream_shop.request;

import com.sujan.tech.dream_shop.model.Category;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductUpdateRequest {
    private Long id;
    private String name;
    private String brand;
    private BigDecimal price;
    private int quantity;
    private String description;
    private Category category;
}
