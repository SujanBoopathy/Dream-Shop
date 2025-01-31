package com.sujan.tech.dream_shop.service.order;

import com.sujan.tech.dream_shop.dto.OrderDto;
import com.sujan.tech.dream_shop.model.Order;

import java.util.List;

public interface IOrderService {
    Order placeOrder(Long userId);
    OrderDto getOrder(Long orderId) throws Exception;
    List<OrderDto> getUserOrders(Long userId);
}
