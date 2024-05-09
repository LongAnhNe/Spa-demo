package com.spa.Online.Spa.controller;

import com.spa.Online.Spa.exception.ProductException;
import com.spa.Online.Spa.model.Product;
import com.spa.Online.Spa.service.ProductServiceImplement;
import com.spa.Online.Spa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api")
@RestController
public class ProductController {
    @Autowired
    private ProductServiceImplement productServiceImplement;
    @Autowired
    private UserService userService;

    @GetMapping("/search")
    public ResponseEntity<List<?>> findRestaurantByName(
            @RequestParam String keyword) {
        List<?> product = productServiceImplement.findByName(keyword);

        return ResponseEntity.ok(product);
    }

    @GetMapping()
    public ResponseEntity<List<?>> getAllRestaurants() {

        List<?> products = productServiceImplement.findAll();


        return ResponseEntity.ok(products);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Product> findProductById(
            @PathVariable Long id) throws ProductException {

        Product product = productServiceImplement.findById(id);
        return ResponseEntity.ok(product);

    }



}
