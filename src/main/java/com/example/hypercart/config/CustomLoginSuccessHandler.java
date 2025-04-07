package com.example.hypercart.config;

import com.example.hypercart.Model.User;
import com.example.hypercart.Repo.UserRepo;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private UserRepo userRepo;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication)
            throws IOException, ServletException {

        String username = authentication.getName();
        User user = userRepo.findByUsername(username).orElse(null);

        if (user != null) {
            String role = user.getRole();  // assuming it's set properly

            switch (role.toUpperCase()) {
                case "ADMIN" -> response.sendRedirect("/AdminDashboard");
                case "SELLER" -> response.sendRedirect("/SellerDashboard");
                case "BUYER" -> response.sendRedirect("/LandingPage");
                default -> response.sendRedirect("/defaultPage");
            }
        } else {
            response.sendRedirect("/login?error=true");
        }
    }
}
