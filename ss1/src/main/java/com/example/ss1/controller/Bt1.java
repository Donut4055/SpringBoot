package com.example.ss1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hello")
public class Bt1 {

    @GetMapping
    public String home() {
        return "hello";
    }

}