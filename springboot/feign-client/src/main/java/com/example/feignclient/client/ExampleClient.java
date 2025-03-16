package com.example.feignclient.client;

import com.example.feignclient.DTO.DataRequestDTO;
import com.example.feignclient.DTO.DataResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "exampleClient", url = "${feign-data.url}")
public interface ExampleClient {

    //Get 요청 (데이터 조회)
    @GetMapping("/api/data/{id}")
    String getData(@PathVariable("id") Long id);

    //post 요청 (데이터 생성)
    @PostMapping("/api/data")
    String createData(@RequestBody DataRequestDTO dataRequestDTO);

    //put 요청 (데이터 수정)
    @PutMapping("/api/data/{id}")
    String updateData(@PathVariable("id") Long id, @RequestBody DataRequestDTO dataRequestDTO);

    //delete 요청 (데이터 삭제)
    @DeleteMapping("/api/data/{id}")
    String deleteData(@PathVariable("id") Long id);

    //전체 조회 (추후에 해야함)
    @GetMapping("/api/data/all")
    List<DataResponseDTO> getAllData();
}
