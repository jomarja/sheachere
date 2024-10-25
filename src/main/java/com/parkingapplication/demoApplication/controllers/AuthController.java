package com.parkingapplication.demoApplication.controllers;

import com.parkingapplication.demoApplication.data.user;
import com.parkingapplication.demoApplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String username,
                               @RequestParam String email,
                               @RequestParam String password) {

        try {
            userService.registerUser(username, email, password);
            return "redirect:/login?registerSuccess";
        } catch (Exception e) {
            return "redirect:/register?error=" + e.getMessage();  // Display error message in URL
        }
    }
}
