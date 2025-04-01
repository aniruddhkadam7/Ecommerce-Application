package com.example.MyFirstApp.Controller;

import com.example.MyFirstApp.Model.User;
import com.example.MyFirstApp.Services.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
            @RequestParam String email) {
        User newUser = new User(username, password);
        newUser.setEmail(email);
        userService.save(newUser);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLoginForm(HttpSession session) {
        System.out.println("Session ID: " + session.getId());
        return "login";
    }

    @PostMapping("/test-register")
    public String testRegister() {
        User testUser = new User("testuser", "testpass");
        testUser.setEmail("test@example.com");
        userService.save(testUser);
        return "redirect:/login";
    }
    @GetMapping("/dashboard")
    public String showDashboard() {
        return "dashboard"; // Resolves to templates/dashboard.html
    }

    @GetMapping("/clear-session")
    public String clearSession(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}