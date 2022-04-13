package com.example.ClinicaDentalApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping String showHomePage(){
        return ("index");
    }
}
