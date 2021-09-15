package com.anylyze.gamification.endpoints;

import com.anylyze.gamification.dto.ProductDto;
import com.anylyze.gamification.dto.ProductWithIdDto;
import com.anylyze.gamification.model.Product;
import com.anylyze.gamification.service.ProductSerice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/rewards")
public class ProductEndpoint {
    @Autowired
    private ProductSerice productSerice;

    @GetMapping
    public List<ProductWithIdDto> getAll() {
        return productSerice.getAll();
    }

    @PostMapping
    public ProductWithIdDto createReward(@RequestBody ProductDto productDto) {
        return productSerice.createReward(productDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable Long id) {
        return ResponseEntity.ok(productSerice.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity updateReward(@PathVariable Long id, @RequestBody ProductDto productDto) {
        try {
            return ResponseEntity.ok(productSerice.update(id, productDto));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteReward(@PathVariable Long id) {
        try {
            productSerice.deleteReward(id);
            return ResponseEntity.ok().build();
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("/{id}/description")
    public ResponseEntity getDescription(@PathVariable Long id) {
        Product product = productSerice.getById(id);
        if (product != null) {
            return ResponseEntity.ok(product.getDescription());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
