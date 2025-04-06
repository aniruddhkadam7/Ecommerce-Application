package com.example.hypercart.Repo;

import com.example.hypercart.Model.Cart;
import com.example.hypercart.Model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepo extends JpaRepository<Cart, Long> {
}
