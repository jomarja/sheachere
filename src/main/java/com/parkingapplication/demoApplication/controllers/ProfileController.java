package com.parkingapplication.demoApplication.controllers;


import com.parkingapplication.demoApplication.repo.userRepository;
import com.parkingapplication.demoApplication.data.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.parkingapplication.demoApplication.repo.userRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.Optional;

@Controller
public class ProfileController {

    @Autowired
    private userRepository userRepository;

    @PostMapping("/profile")
    public String addTenDollars(Principal principal, Model model) {
        // Get logged-in user
        String userEmail = principal.getName();
        Optional<user> loggedInUser = userRepository.findByName(userEmail);

            user user = loggedInUser.get();

            // Add $10 to the user's balance
            user.setPaymentInfo(user.getPaymentInfo() + 10);
            userRepository.save(user);


        return "redirect:/profile"; // Redirect to the profile page to show the updated balance
    }


}