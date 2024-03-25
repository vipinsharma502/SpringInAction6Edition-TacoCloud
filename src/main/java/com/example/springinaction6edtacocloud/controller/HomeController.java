package com.example.springinaction6edtacocloud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    /*
    No Need to call it now
    As in ProjetConfig class
    We have configured it
     */
    //@GetMapping("/")
    public String home() {
        return "home";
    }
}
