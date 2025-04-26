package com.example.hypercart.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collection;

@Component
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication)
            throws IOException, ServletException {

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        String redirectUrl = "/defaultPage"; // fallback

        for (GrantedAuthority authority : authorities) {
            String role = authority.getAuthority(); // e.g. ROLE_ADMIN

            switch (role) {
                case "ROLE_ADMIN" -> redirectUrl = "/AdminLandingPage";
                case "ROLE_SELLER" -> redirectUrl = "/SellerLandingPage";
                case "ROLE_BUYER" -> redirectUrl = "/BuyerLandingPage";
            }
        }

        response.sendRedirect(redirectUrl);
    }
}
