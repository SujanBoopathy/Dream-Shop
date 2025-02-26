package com.sujan.tech.dream_shop.service.cart;

import com.sujan.tech.dream_shop.model.CartItem;

public interface ICartItemService {
    void addItemToCart(Long cartId,Long productId,int quantity) throws Exception;
    void removeItemFromCart(Long cartId, Long productId);
    void updateItemQuantity(Long cartId, Long productId, int quantity);

    CartItem getCartItem(Long cartId, Long productId);
}
