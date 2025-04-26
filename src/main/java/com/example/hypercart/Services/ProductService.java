package com.example.hypercart.Services;

import com.example.hypercart.Model.Product;
import com.example.hypercart.Repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private RedisProductService redisProductService;

    @Autowired
    private ProductRepo productRepo; // Ensure this matches your repository name

    // Assume this is your existing JPA repository method
    private List<Product> getAllProductsFromDB() {
        // Placeholder for your JPA logic
        return null; // Replace with actual DB call
    }

    public Product createProduct(String name, double price, String description, byte[] image) throws IOException {
        // Existing logic to create product
        Product product = new Product(); // Replace with actual creation logic
        redisProductService.invalidateCache(); // Invalidate cache on create
        return product;
    }

    public List<Product> getAllProducts() {
        List<Product> cachedProducts = redisProductService.getCachedProducts();
        if (cachedProducts != null && !cachedProducts.isEmpty()) {
            return cachedProducts; // No casting needed now
        }
        List<Product> products = getAllProductsFromDB();
        redisProductService.cacheProducts(products);
        return products;
    }

    public Product updateProduct(Long id, String name, double price, String description, byte[] image) throws IOException {
        // Existing update logic
        Product product = new Product(); // Replace with actual update logic
        redisProductService.invalidateCache(); // Invalidate cache on update
        return product;
    }

    public void deleteProduct(Long id) {
        // Existing delete logic
        redisProductService.invalidateCache(); // Invalidate cache on delete
    }

    public void incrementSales(Long id) {
        Product product = productRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        product.setSales(product.getSales() != null ? product.getSales() + 1 : 1);
        productRepo.save(product);

        // Invalidate Redis cache if needed
        redisProductService.invalidateCache();
    }
}