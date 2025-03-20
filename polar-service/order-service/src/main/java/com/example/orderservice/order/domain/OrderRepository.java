package com.example.orderservice.order.domain;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

//ReactiveCrud -> findall, save같은 거는 미리 정의되어 있음
public interface OrderRepository extends ReactiveCrudRepository<Order, Long> {
    
}
