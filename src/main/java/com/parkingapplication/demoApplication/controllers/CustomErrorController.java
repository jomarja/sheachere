package com.parkingapplication.demoApplication.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CustomErrorController implements ErrorController {

    @GetMapping("/error")
    public String handleError() {
        // Return a custom error HTML page, such as error.html
        return "error";
    }

    @GetMapping("/access-denied")
    public String accessDenied() {
        // Return a custom access denied page, such as accessDenied.html
        return "error";
    }
}
