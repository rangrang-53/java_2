package com.example.webfrontservice.service;

import com.example.webfrontservice.client.OrderClient;
import com.example.webfrontservice.dto.CreateOrderRequestDTO;
import com.example.webfrontservice.dto.CreateOrderResponseDTO;
import com.example.webfrontservice.dto.GetOrderResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderClient orderClient;

    public CreateOrderResponseDTO createOrder(
            String accessToken,
            CreateOrderRequestDTO createOrderRequestDTO
    ){
        log.info("createOrderRequestDTO:: {}", createOrderRequestDTO);
        return orderClient.createOrder(accessToken, createOrderRequestDTO);
    }

    public List<GetOrderResponseDTO> getOrders(String accessToken) {
        return orderClient.getOrders(accessToken);
    }
}
