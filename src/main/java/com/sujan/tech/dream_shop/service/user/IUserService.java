package com.sujan.tech.dream_shop.service.user;

import com.sujan.tech.dream_shop.model.User;

public interface IUserService {
    User getUserById(Long userId);
//    User createUser(CreateUserRequest request);
//    User updateUser(UserUpdateRequest request, Long userId);
    void deleteUser(Long userId);

//    UserDto convertUserToDto(User user);
}
