package com.example.hypercart.Repo;

import com.example.hypercart.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * This is a repository interface that provides methods to interact with the product table in the database.
 * It extends JpaRepository, which gives us built-in methods like save(), findAll(), deleteById(), etc.
 */
@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
    // No additional methods needed; JpaRepository provides all basic CRUD operations
}