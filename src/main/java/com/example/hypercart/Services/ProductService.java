package com.example.hypercart.Services;

import com.example.hypercart.Model.Product;
import com.example.hypercart.Repo.ProductRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    private ProductRepo productRepo;

    @Transactional
    public Product createProduct(String name, double price, String description, byte[] image) {
        logger.debug("Creating product with name: {}", name);
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setDescription(description);
        product.setImage(image);
        product.setSales(0); // Ensure new products start with 0 sales
        Product savedProduct = productRepo.save(product);
        logger.debug("Product saved with ID: {}", savedProduct.getId());
        return savedProduct;
    }

    public List<Product> getAllProducts() {
        logger.debug("Fetching all products from database");
        List<Product> products = productRepo.findAll();
        logger.debug("Found {} products", products.size());
        return products;
    }

    @Transactional
    public Product updateProduct(Long id, String name, double price, String description, byte[] image) {
        logger.debug("Updating product with ID: {}", id);
        Product product = productRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Product with ID " + id + " not found"));
        product.setName(name);
        product.setPrice(price);
        product.setDescription(description);
        if (image != null) product.setImage(image);
        return productRepo.save(product);
    }

    @Transactional
    public void deleteProduct(Long id) {
        logger.debug("Deleting product with ID: {}", id);
        if (!productRepo.existsById(id)) {
            throw new RuntimeException("Product with ID " + id + " not found");
        }
        productRepo.deleteById(id);
    }
}