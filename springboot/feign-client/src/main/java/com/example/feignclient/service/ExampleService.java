package com.example.feignclient.service;


import com.example.feignclient.DTO.DataRequestDTO;
import com.example.feignclient.DTO.DataResponseDTO;
import com.example.feignclient.client.ExampleClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExampleService {

    private final ExampleClient exampleClient;

    public String getDataById(Long id) {
        return exampleClient.getData(id);
    }

    public String createData(String name, int value) {
        return exampleClient.createData(
                DataRequestDTO.builder()
                        .name(name)
                        .value(value)
                        .build()
        );
    }

    public String updateDataById(Long id, String name, int value) {
        return exampleClient.updateData(id, DataRequestDTO.builder()
                        .name(name)
                        .value(value)
                        .build());
    }

    public String deleteDataById(Long id) {
        return exampleClient.deleteData(id);
    }

    public List<DataResponseDTO> getAllData() {
        return exampleClient.getAllData();
    }
}
