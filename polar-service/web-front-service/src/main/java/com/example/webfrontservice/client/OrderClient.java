package com.example.webfrontservice.client;

import com.example.webfrontservice.dto.CreateOrderRequestDTO;
import com.example.webfrontservice.dto.CreateOrderResponseDTO;
import com.example.webfrontservice.dto.GetOrderResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient(name = "orderClient", url = "${polar.edge-service-url}/orders")
public interface OrderClient {

    @GetMapping
    List<GetOrderResponseDTO> getOrders(
            @RequestHeader ("Authorization") String accessToken
    );

    @PostMapping
    CreateOrderResponseDTO createOrder(
            @RequestHeader ("Authorization") String accessToken,
            @RequestBody CreateOrderRequestDTO createOrderRequestDTO);
}
