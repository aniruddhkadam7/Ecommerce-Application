package com.example.MyFirstApp.Controller;

import com.example.MyFirstApp.Model.User;
import com.example.MyFirstApp.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegisterController {

    @Autowired
    private UserService userService;

    @PostMapping("/register-alt") // Changed from "/register" to "/register-alt"
    public String registerUser(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String email) {
        User newUser = new User(username, password);
        newUser.setEmail(email);
        userService.save(newUser);
        return "redirect:/login";
    }
}