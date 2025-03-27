package com.example.webfrontservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CreateOrderRequestDTO {
    @JsonProperty("isbn")
    private String bookIsbn;

    private int quantity;
}
