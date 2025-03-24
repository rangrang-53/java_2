package com.example.orderservice.book;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookClient bookClient;

    public Flux<Book> getBooks() {
        return bookClient.getBooks()
                //단순히 상수값만 표기할 수 있음
                .defaultIfEmpty(buildMessageBook("Not found!"));
    }

    public Mono<Book> enrollBook(Book book) {
        return bookClient.getBookByIsbn(book.isbn())
                .flatMap(existingBook -> Mono.just(Book.builder().message("Already Exist!").build()))
                //프로세스를 타야할 경우
                .switchIfEmpty(bookClient.enrollBook(book));
    }

    private static Book buildMessageBook(String message) {
        return Book.builder()
                .message(message)
                .build();
    }

    public Mono<Book> updateBook(String isbn, Book book) {
        return bookClient.getBookByIsbn(isbn)
                .flatMap(existingBook -> bookClient.updateBook(isbn, book))
                .defaultIfEmpty(buildMessageBook("Not Found!"));
    }

    public Mono<Void> deleteBook(String isbn) {
        return bookClient.deleteBook(isbn);
    }
}
