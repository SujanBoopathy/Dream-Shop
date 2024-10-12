package com.sujan.tech.dream_shop.request;

@Data
public class AddProductRequest{
    private Long id;
    private String name;
    private String brand;
    private BigDecimal price;
    private int quantity;
    private String description;
    private Category category;
}