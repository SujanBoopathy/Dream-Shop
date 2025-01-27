package com.sujan.tech.dream_shop.service.order;

import com.sujan.tech.dream_shop.dto.OrderDto;
import com.sujan.tech.dream_shop.model.Order;

import java.util.List;

public class OrderService implements IOrderService{

    @Override
    public Order placeOrder(Long userId) {
        return null;
    }

    @Override
    public OrderDto getOrder(Long orderId) {
        return null;
    }

    @Override
    public List<OrderDto> getUserOrders(Long userId) {
        return List.of();
    }
}
