package com.example.catalogservice.domain;

import lombok.Builder;

@Builder
//record = DTO 같은 느낌
public record Book(
        //isbn=책번호
        String isbn,
        String author,
        String title,
        Double price
) {
}
