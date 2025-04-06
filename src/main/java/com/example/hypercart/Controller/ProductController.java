package com.example.hypercart.Controller;

import com.example.hypercart.Model.Product;
import com.example.hypercart.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<Product> createProduct(@RequestBody ProductRequest productRequest) {
        Product product = productService.createProduct(productRequest.getName(), productRequest.getPrice());
        return ResponseEntity.ok(product);
    }

    // DTO for request payload
    static class ProductRequest {
        private String name;
        private double price;

        // Default constructor
        public ProductRequest() {}

        // Parameterized constructor
        public ProductRequest(String name, double price) {
            this.name = name;
            this.price = price;
        }

        // Getters and Setters
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }
    }
}