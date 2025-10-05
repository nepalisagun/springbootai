package com.example.springai.user.controller;

import com.example.springai.common.dto.ApiResponse;
import com.example.springai.common.dto.UserDto;
import com.example.springai.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @GetMapping
    public ResponseEntity<ApiResponse<List<UserDto>>> getAllUsers() {
        ApiResponse<List<UserDto>> response = userService.getAllUsers();
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserDto>> getUserById(@PathVariable Long id) {
        ApiResponse<UserDto> response = userService.getUserById(id);
        if (response.isSuccess()) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
    
    @PostMapping
    public ResponseEntity<ApiResponse<UserDto>> createUser(@Valid @RequestBody UserDto userDto) {
        ApiResponse<UserDto> response = userService.createUser(userDto);
        if (response.isSuccess()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<UserDto>> updateUser(@PathVariable Long id, @Valid @RequestBody UserDto userDto) {
        ApiResponse<UserDto> response = userService.updateUser(id, userDto);
        if (response.isSuccess()) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteUser(@PathVariable Long id) {
        ApiResponse<Void> response = userService.deleteUser(id);
        if (response.isSuccess()) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}
