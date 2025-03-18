package com.example.catalogservice.controller;

import com.example.catalogservice.domain.Book;
import com.example.catalogservice.domain.BookRepository;
import com.example.catalogservice.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookApiController {

    private final BookService bookService;
    private final BookRepository bookRepository;

    @GetMapping
    public Iterable<Book> getBooks() {
        return bookService.viewBookList();
    }

    @PostMapping
    public Book addBook(@RequestBody Book book) {
        return bookService.addBookToCatalog(book);
    }
}
