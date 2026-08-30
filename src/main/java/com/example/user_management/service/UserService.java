package com.example.user_management.service;

import com.example.user_management.dto.request.UserRequest;
import com.example.user_management.dto.response.UserResponse;
import com.example.user_management.entity.User;
import com.example.user_management.repository.UserRepository;
import org.springframework.stereotype.Service;
import com.example.user_management.exception.UserNotFoundException;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream().map(this::toResponse).toList();
    }

    public UserResponse getUserById(Long id) {
        return toResponse(findUserById(id));
    }

    public UserResponse createUser(UserRequest request) {
        User newUser = toEntity(request);
        User savedUser = userRepository.save(newUser);
        return toResponse(savedUser);
    }

    public UserResponse updateUser(Long id, UserRequest request) {
        User existingUser =  findUserById(id);
        existingUser.setName(request.getName());
        existingUser.setEmail(request.getEmail());
        existingUser.setAge(request.getAge());

        User updatedUser = userRepository.save(existingUser);

        return toResponse(updatedUser);
    }

    public void deleteUser(Long id) {
            if (!userRepository.existsById(id)) {
                throw new UserNotFoundException(id);
            }
        userRepository.deleteById(id);
    }

    private User findUserById (Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    private User toEntity(UserRequest request){
        User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setAge(request.getAge());

        return user;
    }

    private UserResponse toResponse(User user) {
        return new UserResponse(
            user.getId(),
            user.getName(),
            user.getEmail(),
            user.getAge()
        );
    }
}
