package com.example.hypercart.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/LandingPage")
    public String landingPage() {
        return "LandingPage"; // Thymeleaf resolves to LandingPage.html
    }

    @GetMapping("/SellerDashboard")
    public String sellerPage() {
        return "SellerDashboard";
    }

    @GetMapping("/AdminDashboard")
    public String adminPage() {
        return "AdminDashboard";
    }
}
