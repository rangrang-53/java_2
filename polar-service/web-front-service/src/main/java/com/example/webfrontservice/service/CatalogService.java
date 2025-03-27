package com.example.webfrontservice.service;

import com.example.webfrontservice.client.CatalogClient;
import com.example.webfrontservice.dto.CreateCatalogRequestDTO;
import com.example.webfrontservice.dto.CreateCatalogResponseDTO;
import com.example.webfrontservice.dto.GetCatalogResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CatalogService {

    private final CatalogClient catalogClient;

    public CreateCatalogResponseDTO createCatalog(
            String accessToken,
            CreateCatalogRequestDTO createCatalogRequestDTO) {
        return catalogClient.createCatalog(accessToken, createCatalogRequestDTO);
    }

    public List<GetCatalogResponseDTO> getCatalog(String accessToken) {
        return catalogClient.getCatalog(accessToken);
    }
}
