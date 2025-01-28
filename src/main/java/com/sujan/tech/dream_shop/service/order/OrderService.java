package com.sujan.tech.dream_shop.service.order;

import com.sujan.tech.dream_shop.dto.OrderDto;
import com.sujan.tech.dream_shop.model.Order;
import com.sujan.tech.dream_shop.repository.OrderRepository;
import com.sujan.tech.dream_shop.repository.ProductRepository;
import com.sujan.tech.dream_shop.service.cart.CartService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService implements IOrderService{
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final CartService cartService;
    private final ModelMapper modelMapper;

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
