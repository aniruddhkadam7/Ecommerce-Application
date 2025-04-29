package com.example.hypercart.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {


    @GetMapping("/Buyer")
    public String showBuyerLandingPage() {
        return "Buyer"; // refers to Buyer.html in templates
    }

    @GetMapping("/Seller")
    public String sellerPage() {
        return "SellerLandingPage";  // refers to SellerLandingPage.html in templates
    }

    @GetMapping("/Admin")
    public String adminPage() {
        return "AdminLandingPage";  // refers to AdminLandingPage.html in templates
    }
}
