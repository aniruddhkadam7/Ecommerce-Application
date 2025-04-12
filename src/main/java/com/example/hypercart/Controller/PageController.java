package com.example.hypercart.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {


    @GetMapping("/BuyerLandingPage")
    public String showBuyerLandingPage() {
        return "BuyerLandingPage"; // refers to BuyerLandingPage.html in templates
    }

    @GetMapping("/SellerLandingPage")
    public String sellerPage() {
        return "SellerLandingPage";  // refers to SellerLandingPage.html in templates
    }

    @GetMapping("/AdminLandingPage")
    public String adminPage() {
        return "AdminLandingPage";  // refers to AdminLandingPage.html in templates
    }
}
