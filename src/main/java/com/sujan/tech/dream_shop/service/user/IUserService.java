package com.sujan.tech.dream_shop.service.user;

import com.sujan.tech.dream_shop.dto.UserDto;
import com.sujan.tech.dream_shop.model.User;
import com.sujan.tech.dream_shop.request.CreateUserRequest;
import com.sujan.tech.dream_shop.request.UserUpdateRequest;

public interface IUserService {
    User getUserById(Long userId) throws Exception;
    User createUser(CreateUserRequest request) throws Exception;
    User updateUser(UserUpdateRequest request, Long userId) throws Exception;
    void deleteUser(Long userId) throws Exception;

    UserDto convertUserToDto(User user) throws Exception;
}
