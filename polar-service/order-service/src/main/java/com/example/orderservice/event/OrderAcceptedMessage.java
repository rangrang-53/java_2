package com.example.orderservice.event;

public record OrderAcceptedMessage(
        Long orderId
) {
}
