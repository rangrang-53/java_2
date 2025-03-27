package com.example.webfrontservice.controller;

import com.example.webfrontservice.dto.CreateOrderRequestDTO;
import com.example.webfrontservice.dto.CreateOrderResponseDTO;
import com.example.webfrontservice.dto.GetCatalogResponseDTO;
import com.example.webfrontservice.dto.GetOrderResponseDTO;
import com.example.webfrontservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@RequiredArgsConstructor
@RequestMapping("/webs/api/order")
public class OrderApiController {

    private final OrderService orderService;

    @PostMapping
    public CreateOrderResponseDTO createOrder(
            @RequestHeader(value = AUTHORIZATION, required = false)String accessToken,
            @RequestBody CreateOrderRequestDTO createOrderRequestDTO) {

        return orderService.createOrder(accessToken, createOrderRequestDTO);

    }

    @GetMapping
    public List<GetOrderResponseDTO> getOrder(
            @RequestHeader(value = AUTHORIZATION, required = false) String accessToken
    ){
        return orderService.getOrders(accessToken);
    }

}
