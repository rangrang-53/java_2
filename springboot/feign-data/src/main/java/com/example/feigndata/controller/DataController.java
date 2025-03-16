package com.example.feigndata.controller;


import com.example.feigndata.dto.DataRequestDTO;
import com.example.feigndata.dto.DataResponseDTO;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/data")
public class DataController {

    private Map<Long, DataResponseDTO> dataStore = new HashMap<>();
    private Long idCnt = 1L;

    @PostConstruct
    public void initDataSource(){
        dataStore.put(idCnt++, new DataResponseDTO(1L, "Item 1",100));
        dataStore.put(idCnt++, new DataResponseDTO(1L, "Item 2",200));
        dataStore.put(idCnt++, new DataResponseDTO(1L, "Item 3",300));
        dataStore.put(idCnt++, new DataResponseDTO(1L, "Item 4",400));
        dataStore.put(idCnt++, new DataResponseDTO(1L, "Item 5",500));
    }

    @GetMapping("/{id}")
    public DataResponseDTO getData(@PathVariable Long id){
        log.info("[feign data] select");
        return dataStore.get(id);
    }

    @PostMapping
    public DataResponseDTO createData(@RequestBody DataRequestDTO dataRequestDTO){
        log.info("[feign data] create");
        DataResponseDTO newData = DataResponseDTO.builder()
                .id(idCnt)
                .name(dataRequestDTO.getName())
                .value(dataRequestDTO.getValue())
                .build();

        dataStore.put(idCnt++, newData);

        return newData;
    }

    @PutMapping("/{id}")
    public DataResponseDTO updateData(@PathVariable Long id, @RequestBody DataRequestDTO dataRequestDTO){
        log.info("[feign data] update");
        DataResponseDTO dataResponseDTO = dataStore.get(id);

        dataResponseDTO.setValue(dataRequestDTO.getValue());
        dataStore.put(id, dataResponseDTO);

        return dataResponseDTO;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id){
        log.info("[feign data] delete");
        DataResponseDTO removed = dataStore.remove(id);
        if(removed != null){
            //예외 처리 하기
        }
        return "Data with ID " + removed.getId() + " was deleted";
    }

    @GetMapping("/all")
    public List<DataResponseDTO> getAll(){
        log.info("[feign data] getAll");

        List<DataResponseDTO> results = new ArrayList<>();

        for (Long id : dataStore.keySet()) {
            DataResponseDTO dataResponseDTO = dataStore.get(id);
            results.add(dataResponseDTO);
        }

        return results;
    }
}
