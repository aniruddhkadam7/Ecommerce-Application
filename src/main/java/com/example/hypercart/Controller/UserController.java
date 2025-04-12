package com.example.hypercart.Controller;

import com.example.hypercart.Model.User;
import com.example.hypercart.Services.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String showRegistrationForm() {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String email,
            @RequestParam String role,
            Model model) {

        User newUser = new User(username, password, email);
        newUser.setRole(role);

        try {
            userService.save(newUser);
            return "redirect:/login";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "register"; // Return to registration page with error message
        }
    }

    @GetMapping("/login")   // login endpoint api
    public String showLoginForm(HttpSession session) {
        System.out.println("Session ID: " + session.getId());
        return "login";
    }

    @GetMapping("/clear-session")
    public String clearSession(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}