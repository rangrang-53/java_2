package com.example.webfrontservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/webs/orders")
public class OrderController {

    @GetMapping
    public String order(){
        return "order";
    }

    @GetMapping("/check")
    public String getOrder(){
        return "order-check";
    }
}
