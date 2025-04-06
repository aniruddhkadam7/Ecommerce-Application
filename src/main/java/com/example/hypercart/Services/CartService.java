package com.example.hypercart.Services;

import com.example.hypercart.Model.Cart;
import com.example.hypercart.Model.Product;
import com.example.hypercart.Repo.CartRepo;
import com.example.hypercart.Repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    @Autowired
    private CartRepo cartRepo;

    @Autowired
    private ProductRepo productRepo;

    public Cart addProductToCart(Long productId, int quantity, Long userId) {

        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product with ID " + productId + " not found"));

        Cart cart = new Cart(product, quantity, userId);
        return cartRepo.save(cart);
    }

}
