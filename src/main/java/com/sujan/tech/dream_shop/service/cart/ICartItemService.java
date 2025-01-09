package com.sujan.tech.dream_shop.service.cart;

import com.sujan.tech.dream_shop.model.CartItem;

public interface ICartItemService {
    void addItemToCart(Long cartId,Long productId,int quantity);
}
