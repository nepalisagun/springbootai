package com.example.springai.user.service;

import com.example.springai.common.dto.ApiResponse;
import com.example.springai.common.dto.UserDto;
import com.example.springai.user.entity.User;
import com.example.springai.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    public ApiResponse<List<UserDto>> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserDto> userDtos = users.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        return ApiResponse.success(userDtos);
    }
    
    public ApiResponse<UserDto> getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return ApiResponse.success(convertToDto(user.get()));
        } else {
            return ApiResponse.error("User not found with id: " + id);
        }
    }
    
    public ApiResponse<UserDto> createUser(UserDto userDto) {
        if (userRepository.existsByUsername(userDto.getUsername())) {
            return ApiResponse.error("Username already exists");
        }
        
        if (userRepository.existsByEmail(userDto.getEmail())) {
            return ApiResponse.error("Email already exists");
        }
        
        User user = convertToEntity(userDto);
        User savedUser = userRepository.save(user);
        return ApiResponse.success(convertToDto(savedUser), "User created successfully");
    }
    
    public ApiResponse<UserDto> updateUser(Long id, UserDto userDto) {
        Optional<User> existingUser = userRepository.findById(id);
        if (existingUser.isEmpty()) {
            return ApiResponse.error("User not found with id: " + id);
        }
        
        User user = existingUser.get();
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        
        User updatedUser = userRepository.save(user);
        return ApiResponse.success(convertToDto(updatedUser), "User updated successfully");
    }
    
    public ApiResponse<Void> deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return ApiResponse.success(null, "User deleted successfully");
        } else {
            return ApiResponse.error("User not found with id: " + id);
        }
    }
    
    private UserDto convertToDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        return dto;
    }
    
    private User convertToEntity(UserDto dto) {
        return new User(dto.getUsername(), dto.getEmail(), dto.getFirstName(), dto.getLastName());
    }
}
