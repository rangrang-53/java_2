package com.example.orderservice.order.domain;

import com.example.orderservice.book.Book;
import com.example.orderservice.book.BookClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.example.orderservice.order.domain.OrderStatus.*;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final BookClient bookClient;

    public Flux<Order> findAll() {
        return orderRepository.findAll();
    }

    public Mono<Order> submitOrder(String isbn, int quantity) {
        return bookClient.getBookByIsbn(isbn)
                //결과만 매핑한 상태
                .map(book -> buildAcceptedOrder(book, quantity))
                .defaultIfEmpty(buildRejectedOrder(isbn, quantity))

                //order DB로 저장하는 과정
                //flatMap = Mono<Mono<Order>>를 Mono<Order>로 평탄화
                .flatMap(orderRepository::save);
    }

    //catalog 서비스에 요청한 book이 있을때
    private static Order buildAcceptedOrder(Book book, int quantity) {
        return Order.builder()
                .bookIsbn(book.isbn())
                .bookName(book.title() + "-" + book.author())
                .bookPrice(book.price())
                .quantity(quantity)
                .status(ACCEPTED)
                .build();
    }

    private static Order buildRejectedOrder(String bookIsbn, int quantity) {
        return Order.builder()
                .bookIsbn(bookIsbn)
                .quantity(quantity)
                .status(REJECTED)
                .build();
    }
}
