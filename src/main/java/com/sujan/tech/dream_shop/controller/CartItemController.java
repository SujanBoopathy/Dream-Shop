package com.sujan.tech.dream_shop.controller;

import com.sujan.tech.dream_shop.service.cart.ICartItemService;
import com.sujan.tech.dream_shop.service.cart.ICartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/cartItems")
public class CartItemController {
    private final ICartItemService cartItemService;
    private final ICartService cartService;
}
