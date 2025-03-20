package com.example.orderservice.book;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor

//=feign client
public class BookClient {

    private static final String BOOKS_ROOT_API = "/books/";
    private final WebClient webClient;

    //Mono = 서빙하는 직원 (단일 건)
    //FLUX = 서빙하는 직원 (getByALL)
    public Mono<Book> getBookByIsbn(String isbn) {
        return webClient.get()
                .uri(BOOKS_ROOT_API + "/" + isbn)
                .retrieve() // 요청을 보내고 응답을 기다린다
                .bodyToMono(Book.class)
                .timeout(Duration.ofSeconds(3))
                //예외를 mono에 감싸서 준다
                .onErrorResume(WebClientResponseException.NotFound.class, exception -> Mono.empty())
                //0.01초로 세 번 더 시도해봐라
                .retryWhen(
                        Retry.backoff(3, Duration.ofMillis(100))
                )
                .onErrorResume(Exception.class, exception -> Mono.empty());
    }
}
