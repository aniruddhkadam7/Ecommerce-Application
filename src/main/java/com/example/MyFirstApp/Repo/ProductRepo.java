package com.example.MyFirstApp.Repo;

import com.example.MyFirstApp.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product, Long> {
}