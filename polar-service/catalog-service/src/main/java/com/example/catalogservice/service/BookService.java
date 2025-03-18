package com.example.catalogservice.service;

import com.example.catalogservice.domain.Book;
import com.example.catalogservice.domain.BookRepository;
import com.example.catalogservice.exception.BookAlreadyException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public Iterable<Book> viewBookList() {
        return bookRepository.findAll();
    }

    public Book addBookToCatalog(Book book) {

        if(bookRepository.existsByIsbn(book.isbn())){
            throw new BookAlreadyException(book.isbn());
        }

        return bookRepository.save(book);
    }
}
