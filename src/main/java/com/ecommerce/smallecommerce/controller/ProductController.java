package com.ecommerce.smallecommerce.controller;

import com.ecommerce.smallecommerce.dto.ProductRequestDto;
import com.ecommerce.smallecommerce.dto.ProductResponseDto;
import com.ecommerce.smallecommerce.model.Product;
import com.ecommerce.smallecommerce.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/product")
public class ProductController {
    private final ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/get/all")
    public List<ProductResponseDto> getProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/get/{id}")
    public ProductResponseDto getProduct(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @PostMapping("/create")
    public ProductResponseDto createProduct(@RequestBody ProductRequestDto productRequest) {
        Product product = productService.createProduct(productRequest);
        return toDto(product);
    }

    @PutMapping("/update/{id}") //TODO modify this function to allow update one thing
    public Product updateProduct(@PathVariable Long id, @RequestBody Product product) {
        return productService.updateProduct(id, product);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }

    private ProductResponseDto toDto(Product product) {
        return new ProductResponseDto(product.getId(), product.getName(), product.getDescription(), product.getPrice());
    }
}
