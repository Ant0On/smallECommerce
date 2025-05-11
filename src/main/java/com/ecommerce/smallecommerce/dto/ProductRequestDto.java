package com.ecommerce.smallecommerce.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ProductRequestDto {

    @NotBlank(message = "Name is required")
    @Size(min = 5, max = 100, message = "Name should be between 5 and 100 characters")
    private String name;
    @Size(min = 50, max = 400, message = "Description should be between 50 and 400 characters")
    private String description;
    @Min(0)
    private double price;
    @Min(0)
    private int quantity;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
