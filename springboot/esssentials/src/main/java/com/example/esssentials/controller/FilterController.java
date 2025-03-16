package com.example.esssentials.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FilterController {

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/api/hello")
    public String apiHello() {
        System.out.println("[apiHello]");
        return "api Hello";
    }
}
