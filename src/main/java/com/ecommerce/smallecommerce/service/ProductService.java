package com.ecommerce.smallecommerce.service;

import com.ecommerce.smallecommerce.dto.ProductRequestDto;
import com.ecommerce.smallecommerce.dto.ProductResponseDto;
import com.ecommerce.smallecommerce.exception.ProductNotFoundException;
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
        Product product = productRepository.findById(id).orElseThrow(
                () -> new ProductNotFoundException(id)
        );
        return toDto(product);
    }

    public ProductResponseDto createProduct(ProductRequestDto productRequest) {
        Product product = fromDto(productRequest);
        return toDto(productRepository.save(product));
    }

    public ProductResponseDto updateProduct(Long id, ProductRequestDto updatedProduct) {
        Product product = productRepository.findById(id).orElseThrow(
                () -> new ProductNotFoundException(id)
        );
        product.setName(updatedProduct.getName());
        product.setDescription(updatedProduct.getDescription());
        product.setPrice(updatedProduct.getPrice());
        product.setQuantity(updatedProduct.getQuantity());
        return toDto(productRepository.save(product));
    }

    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException(id);
        }
        productRepository.deleteById(id);
    }

    private ProductResponseDto toDto(Product product) {
        return new ProductResponseDto(product.getId(), product.getName(), product.getDescription(), product.getPrice());
    }

    private Product fromDto(ProductRequestDto dto) {
        return new Product(dto.getName(), dto.getDescription(), dto.getPrice(), dto.getQuantity());
    }
}
