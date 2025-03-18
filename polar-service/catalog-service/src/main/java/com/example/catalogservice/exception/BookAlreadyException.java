package com.example.catalogservice.exception;

public class BookAlreadyException extends RuntimeException {
    public BookAlreadyException(String isbn) {

        super("Book already exists: " + isbn);
    }
}
