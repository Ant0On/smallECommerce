package com.ecommerce.smallecommerce.dto;

import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public class OrderRequestDto {
    @NotEmpty(message = "At least one product is required")
    private List<Long> productIds;

    public List<Long> getProductIds() {
        return productIds;
    }

    public void setProductIds(List<Long> productIds) {
        this.productIds = productIds;
    }
}
