package com.example.catalogservice.controller;

import com.example.catalogservice.domain.Book;
import com.example.catalogservice.domain.BookRepository;
import com.example.catalogservice.service.BookService;
import jakarta.validation.Valid;
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

    @GetMapping("/{isbn}")
    public Book getBook(@PathVariable String isbn) {
        return bookService.viewBook(isbn);
    }

    @PostMapping
    public Book addBook(@Valid @RequestBody Book book) {
        return bookService.addBookToCatalog(book);
    }

    @PutMapping("/{isbn}")
    public Book updateBook(@PathVariable String isbn, @Valid @RequestBody Book book) {
        return bookService.editBookDetails(isbn, book);
    }

    @DeleteMapping("/{isbn}")
    public void deleteBook(@PathVariable String isbn) {
        bookService.removeBookFromCatalog(isbn);
    }
}
