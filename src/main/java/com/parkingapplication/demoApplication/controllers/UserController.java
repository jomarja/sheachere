package com.parkingapplication.demoApplication.controllers;

import com.parkingapplication.demoApplication.data.parkinglot;
import com.parkingapplication.demoApplication.repo.userRepository;
import com.parkingapplication.demoApplication.data.reservation;
import com.parkingapplication.demoApplication.data.user;
import com.parkingapplication.demoApplication.model.Role;
import com.parkingapplication.demoApplication.model.User;
import com.parkingapplication.demoApplication.service.ReservationService;
import com.parkingapplication.demoApplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    userRepository userRepository;

    @Autowired
    UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/profile")
    public String showUserProfile(Model model, Principal principal) {
        String userEmail = principal.getName();
        user loggedInUser = userRepository.findByName(userEmail).orElse(null);

        System.out.println("Username: " + principal.getName());

        List<reservation> userReservations = ReservationService.getReservationsByUserID(loggedInUser.getUserID());
        model.addAttribute("reservations", userReservations);

        if (principal != null) {
            User user = userService.findByUsername(principal.getName());

            if (user.getRole() == Role.ADMIN) {
                return "redirect:/admin";
            }

            model.addAttribute("payment",user.getPaymentInfo());
        }

        return "profile";
    }

    @GetMapping("/admin")
    public String adminDashboard(Model model, Principal principal) {
        Iterable<user> parkingLots = userRepository.findAll();
        model.addAttribute("users", parkingLots);
        return "admin";
    }
}