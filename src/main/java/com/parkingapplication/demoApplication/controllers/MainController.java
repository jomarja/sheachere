package com.parkingapplication.demoApplication.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {


    @GetMapping("/")
    public String greeting(Model model){
        model.addAttribute("title", "main page");
        return "Home1";
    }

    @GetMapping("/reservation")
    public String reservattion(Model model){

        return "Reservation";
    }
}