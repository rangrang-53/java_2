package com.example.webfrontservice.client;

import com.example.webfrontservice.dto.CreateCatalogRequestDTO;
import com.example.webfrontservice.dto.CreateCatalogResponseDTO;
import com.example.webfrontservice.dto.GetCatalogResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient(name = "catalogClient", url = "${polar.edge-service-url}/books")
public interface CatalogClient {

    @GetMapping
    List<GetCatalogResponseDTO> getCatalog(
            @RequestHeader("Authorization") String accessToken);
    
    //access token이 header에 실림
    @PostMapping
    CreateCatalogResponseDTO createCatalog(
            @RequestHeader("Authorization") String accessToken,
            @RequestBody CreateCatalogRequestDTO createCatalogRequestDTO
    );
    
}
