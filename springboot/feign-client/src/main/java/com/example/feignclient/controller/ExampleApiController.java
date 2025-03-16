package com.example.feignclient.controller;


import com.example.feignclient.DTO.DataRequestDTO;
import com.example.feignclient.DTO.DataResponseDTO;
import com.example.feignclient.client.ExampleClient;
import com.example.feignclient.service.ExampleService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/feign/data")
public class ExampleApiController {

    private final ExampleService exampleService;

    @GetMapping("/{id}")
    public String getData(@PathVariable Long id) {
        System.out.println("[CLIENT] 조회");
        return exampleService.getDataById(id);
    }

    @PostMapping
    public String createData(@RequestParam String name, @RequestParam int value) {
        System.out.println("[CLIENT] 입력");
        return exampleService.createData(name, value);
    }

    @PutMapping("/{id}")
    public String updateData(@PathVariable Long id, @RequestParam String name, @RequestParam int value) {
        System.out.println("[CLIENT] 수정");
        return exampleService.updateDataById(id, name, value);
    }

    @DeleteMapping("/{id}")
    public String deleteData(@PathVariable Long id) {
        System.out.println("[CLIENT] 삭제");
        return exampleService.deleteDataById(id);
    }

    @GetMapping("/all")
    public List<DataResponseDTO> getAllData() {
        return exampleService.getAllData();
    }
}

