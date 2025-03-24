package com.example.orderservice.event;

public record OrderDispatchedMessage(
    Long orderId
) {
}
