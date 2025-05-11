package com.ecommerce.smallecommerce.dto;

import java.util.List;

public class OrderRequestDto {
    private List<Long> productIds;

    public List<Long> getProductIds() {
        return productIds;
    }

    public void setProductIds(List<Long> productIds) {
        this.productIds = productIds;
    }
}
