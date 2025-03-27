package com.example.webfrontservice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class GetCatalogResponseDTO {
    private String isbn;
    private String title;
    private String author;
    private Double price;
}
