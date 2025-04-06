package com.example.hypercart.Controller;

import com.example.hypercart.Model.Cart;
import com.example.hypercart.Repo.CartRepo;
import com.example.hypercart.Services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/cart")
public class CartController {

    @Autowired
    private CartService cartService;


    @PostMapping("/add")
    public ResponseEntity<Cart> addToCart(@RequestBody CartRequest cartRequest) {
        Cart cart = cartService.addProductToCart(cartRequest.getProductId(), cartRequest.getQuantity(), cartRequest.getUserId());
        return ResponseEntity.ok(cart);
    }

    // DTO for request payload
    class CartRequest {
        private Long productId;
        private int quantity;
        private Long userId;

        // Default constructor
        public CartRequest() {}

        // Parameterized constructor
        public CartRequest(Long productId, int quantity, Long userId) {
            this.productId = productId;
            this.quantity = quantity;
            this.userId = userId;
        }

        // Getters and Setters
        public Long getProductId() {
            return productId;
        }

        public void setProductId(Long productId) {
            this.productId = productId;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public Long getUserId() {
            return userId;
        }

        public void setUserId(Long userId) {
            this.userId = userId;
        }
    }

}
