package com.sujan.tech.dream_shop.service.cart;

import com.sujan.tech.dream_shop.model.Cart;
import com.sujan.tech.dream_shop.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class CartService implements ICartService {
    private final CartRepository cartRepository;

    public Cart getCart(Long id) {
        Cart cart = cartRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Cart not found")
        );
        BigDecimal totalAmount = cart.getTotalAmount();
        cart.setTotalAmount(totalAmount);
        return cartRepository.save(cart);
    }
}