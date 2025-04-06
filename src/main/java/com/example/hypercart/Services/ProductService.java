package com.example.hypercart.Services;

import com.example.hypercart.Model.Product;
import com.example.hypercart.Repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    @Transactional
    public Product createProduct(String name, double price) {
        Product product = new Product(name, price);
        return productRepo.save(product);
    }
}