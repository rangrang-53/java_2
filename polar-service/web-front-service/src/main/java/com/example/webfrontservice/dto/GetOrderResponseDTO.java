package com.example.webfrontservice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class GetOrderResponseDTO {
    private Long id;
    private String bookIsbn;
    private String bookName;
    private Double bookPrice;
    private int quantity;
    private String status;
    private LocalDateTime createdDate;
}
