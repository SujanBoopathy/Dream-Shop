package com.sujan.tech.dream_shop.service.user;


import com.sujan.tech.dream_shop.dto.UserDto;
import com.sujan.tech.dream_shop.model.User;
import com.sujan.tech.dream_shop.repository.UserRepository;
import com.sujan.tech.dream_shop.request.CreateUserRequest;
import com.sujan.tech.dream_shop.request.UserUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
   
    @Override
    public User getUserById(Long userId) throws Exception {
        return userRepository.findById(userId)
                .orElseThrow(() -> new Exception("User not found!"));
    }

    @Override
    public User createUser(CreateUserRequest request) throws Exception {
        return  Optional.of(request)
                .filter(user -> !userRepository.existsByEmail(request.getEmail()))
                .map(req -> {
                    User user = new User();
                    user.setEmail(request.getEmail());
                    user.setPassword(request.getPassword());
                    user.setFirstName(request.getFirstName());
                    user.setLastName(request.getLastName());
                    return  userRepository.save(user);
                }).orElseThrow(()-> new Exception("user not created"));
    }

    @Override
    public User updateUser(UserUpdateRequest request, Long userId) throws Exception {
        return  userRepository.findById(userId).map(existingUser ->{
            existingUser.setFirstName(request.getFirstName());
            existingUser.setLastName(request.getLastName());
            return userRepository.save(existingUser);
        }).orElseThrow(() -> new Exception("User not found!"));

    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.findById(userId).ifPresentOrElse(userRepository :: delete, () ->{
            try {
                throw new Exception("User not found!");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public UserDto convertUserToDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }
}
