package com.example.MyFirstApp.Controller;

import com.example.MyFirstApp.Model.Product;
import com.example.MyFirstApp.Repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepo productRepo;

    // Public endpoint to view all products
    @GetMapping("/public/All")
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    // Admin-only endpoint to add a product
    @PostMapping("/admin/add")
    public Product addProduct(@RequestBody Product product) {
        return productRepo.save(product);
    }

    // Seller-only endpoint to update a product
    @PutMapping("/seller/update/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product product) {
        Product existingProduct = productRepo.findById(id).orElseThrow();
        existingProduct.setName(product.getName());
        existingProduct.setPrice(product.getPrice());
        return productRepo.save(existingProduct);
    }

    // Customer-only endpoint to view a specific product
    @GetMapping("/customer/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productRepo.findById(id).orElseThrow();
    }
}