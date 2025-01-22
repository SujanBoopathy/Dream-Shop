package com.sujan.tech.dream_shop.service.cart;

import com.sujan.tech.dream_shop.model.Cart;

import java.math.BigDecimal;

public interface ICartService {
    Cart getCart(Long id);
    void clearCart(Long id);
    BigDecimal getTotalPrice(Long id);
    public Long initializeNewCart();
}