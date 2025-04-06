package com.example.hypercart.Repo;

import com.example.hypercart.Model.Cart;
import com.example.hypercart.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {

}
