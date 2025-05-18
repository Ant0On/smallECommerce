package com.ecommerce.smallecommerce.service;

import com.ecommerce.smallecommerce.dto.UserRequestDto;
import com.ecommerce.smallecommerce.dto.UserResponseDto;
import com.ecommerce.smallecommerce.enums.UserRole;
import com.ecommerce.smallecommerce.model.Order;
import com.ecommerce.smallecommerce.model.User;
import com.ecommerce.smallecommerce.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponseDto register(UserRequestDto dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRole(dto.getUserRole() != null ? dto.getUserRole() : UserRole.ROLE_USER);
        user.setOrders(List.of());

        User saved = userRepository.save(user);
        return new UserResponseDto(
                saved.getId(),
                saved.getUsername(),
                saved.getRole().name(),
                saved.getOrders().stream().map(Order::getId).toList()
        );
    }
}