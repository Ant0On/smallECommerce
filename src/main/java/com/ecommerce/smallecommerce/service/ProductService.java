package com.ecommerce.smallecommerce.service;

import com.ecommerce.smallecommerce.dto.ProductRequestDto;
import com.ecommerce.smallecommerce.dto.ProductResponseDto;
import com.ecommerce.smallecommerce.model.Product;
import com.ecommerce.smallecommerce.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductResponseDto> getAllProducts() {
        return productRepository.findAll().stream()
                .map(this::toDto)
                .toList();
    }

    public ProductResponseDto getProductById(Long id) {
        Product product = productRepository.findById(id).orElseThrow();
        return toDto(product);
    }

    public Product createProduct(ProductRequestDto productRequest) {
        Product product = new Product(productRequest.getName(), productRequest.getDescription(), productRequest.getPrice(), productRequest.getQuantity());
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, Product updatedProduct) {
        Product product = getProductById(id);
        product.setName(updatedProduct.getName());
        product.setDescription(updatedProduct.getDescription());
        product.setPrice(updatedProduct.getPrice());
        product.setQuantity(updatedProduct.getQuantity());
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    private ProductResponseDto toDto(Product product) {
        return new ProductResponseDto(product.getId(), product.getName(), product.getDescription(), product.getPrice());
    }
}
