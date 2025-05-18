package com.ecommerce.smallecommerce.dto;

import java.util.List;

public class UserResponseDto {
    private Long id;
    private String username;
    private String role;
    private List<Long> orderIds;

    public Long getId() { return id; }
    public String getUsername() { return username; }
    public String getRole() { return role; }
    public List<Long> getOrderIds() { return orderIds; }

    public void setId(Long id) { this.id = id; }
    public void setUsername(String username) { this.username = username; }
    public void setRole(String role) { this.role = role; }
    public void setOrderIds(List<Long> orderIds) { this.orderIds = orderIds; }

    public UserResponseDto(Long id, String username, String role, List<Long> orderIds) {
        this.id = id;
        this.username = username;
        this.role = role;
        this.orderIds = orderIds;
    }
}