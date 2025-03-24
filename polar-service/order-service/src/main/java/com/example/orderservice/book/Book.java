package com.example.orderservice.book;

import lombok.Builder;

@Builder
public record Book(
        String isbn,
        String title,
        String author,
        Double price,
        String message
) {
}
