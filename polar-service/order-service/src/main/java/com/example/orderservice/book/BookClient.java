package com.example.orderservice.book;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;

@Component
@RequiredArgsConstructor
@Slf4j

//=feign client
public class BookClient {

    private static final String BOOKS_ROOT_API = "/books";
    private final WebClient webClient;

    //Mono = 서빙하는 직원 (단일 건)
    //FLUX = 서빙하는 직원 (getByALL)
    public Mono<Book> getBookByIsbn(String isbn) {

        return webClient.get()
                .uri(BOOKS_ROOT_API + "/" + isbn)
                .retrieve() // 요청을 보내고 응답을 기다린다
                .bodyToMono(Book.class)
                .doOnNext(book -> log.info("Book fetched from catalog: {}", book))
                .doOnError(ex -> log.error("Error fetching book: ", ex))
                .timeout(Duration.ofSeconds(3))
                //예외를 mono에 감싸서 준다
                .onErrorResume(WebClientResponseException.NotFound.class, exception -> Mono.empty())
                //0.01초로 세 번 더 시도해봐라
                .retryWhen(
                        Retry.backoff(3, Duration.ofMillis(100))
                )
                .onErrorResume(Exception.class, ex -> {
                    log.error("General error: ", ex);
                    return Mono.empty();
                });
    }

    public Flux<Book> getBooks() {
        return webClient.get()
                .uri(BOOKS_ROOT_API)
                .retrieve()
                .bodyToFlux(Book.class)
                .timeout(Duration.ofSeconds(3))
                //예외를 mono에 감싸서 준다
                .onErrorResume(WebClientResponseException.NotFound.class, exception -> Mono.empty())
                //0.01초로 세 번 더 시도해봐라
                .retryWhen(
                        Retry.backoff(3, Duration.ofMillis(100))
                )
                .onErrorResume(Exception.class, exception -> Mono.empty());
    }

    public Mono<Book> enrollBook(Book book) {
        return webClient.post()
                .uri(BOOKS_ROOT_API)
                .bodyValue(book)
                .retrieve()
                .bodyToMono(Book.class)
                .timeout(Duration.ofSeconds(3))
                .onErrorResume(WebClientResponseException.NotFound.class, exception -> Mono.empty())
                .retryWhen(
                        Retry.backoff(3, Duration.ofMillis(100))
                )
                .onErrorResume(Exception.class, exception -> Mono.empty());
    }

    public Mono<Book> updateBook(String isbn, Book book) {
        return webClient.put()
                .uri(BOOKS_ROOT_API + "/" + isbn)
                .bodyValue(book)
                .retrieve()
                .bodyToMono(Book.class)
                .timeout(Duration.ofSeconds(3))
                .onErrorResume(WebClientResponseException.NotFound.class, exception -> Mono.empty())
                .retryWhen(
                        Retry.backoff(3, Duration.ofMillis(100))
                )
                .onErrorResume(Exception.class, exception -> Mono.empty());
    }

    public Mono<Void> deleteBook(String isbn) {
        return webClient.delete()
                .uri(BOOKS_ROOT_API + "/" +isbn)
                .retrieve()
                .bodyToMono(Void.class)
                .timeout(Duration.ofSeconds(3))
                .onErrorResume(WebClientResponseException.NotFound.class, exception -> Mono.empty())
                .retryWhen(
                        Retry.backoff(3, Duration.ofMillis(100))
                )
                .onErrorResume(Exception.class, exception -> Mono.empty());
    }
}
