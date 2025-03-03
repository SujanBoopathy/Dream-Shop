package com.sujan.tech.dream_shop.dto;

import com.sujan.tech.dream_shop.dto.*;
import lombok.Data;

import java.util.List;

@Data
public class UserDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private List<OrderDto> orders;
    private CartDto cart;
}
