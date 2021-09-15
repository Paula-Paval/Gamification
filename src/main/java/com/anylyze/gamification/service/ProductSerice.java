package com.anylyze.gamification.service;

import com.anylyze.gamification.dto.ProductDto;
import com.anylyze.gamification.dto.ProductWithIdDto;
import com.anylyze.gamification.model.Product;
import com.anylyze.gamification.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductSerice {
    @Autowired
    private ProductRepository productRepository;

    public List<ProductWithIdDto> getAll() {
        return productRepository.findAll()
                .stream()
                .map(this::convertToProductDto)
                .collect(Collectors.toList());
    }

    public ProductWithIdDto createReward(ProductDto productDto) {
        Product product = convertToProduct(productDto);
        product = productRepository.save(product);
        return convertToProductDto(product);
    }

    public Product getById(Long id) {
        Optional<Product> rewards = productRepository.findById(id);
        return rewards.orElse(null);
    }

    public ProductDto update(Long id, ProductDto productDto) {
        Product product = productRepository.getOne(id);
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setCredits(productDto.getCredits());
        product.setImageurl(productDto.getImageurl());
        productRepository.save(product);
        return productDto;
    }

    public void deleteReward(Long id) {
        productRepository.deleteById(id);
    }

    private Product convertToProduct(ProductDto productDto) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setCredits(productDto.getCredits());
        product.setImageurl(productDto.getImageurl());
        return product;
    }

    private ProductWithIdDto convertToProductDto(Product product) {
        ProductWithIdDto productWithIdDto = new ProductWithIdDto();
        productWithIdDto.setId(product.getId());
        productWithIdDto.setName(product.getName());
        productWithIdDto.setCredits(product.getCredits());
        productWithIdDto.setDescription(product.getDescription());
        productWithIdDto.setImageurl(product.getImageurl());
        return productWithIdDto;
    }
}
