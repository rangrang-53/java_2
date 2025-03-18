package com.example.catalogservice.domain;

import org.springframework.stereotype.Repository;

import java.util.Optional;

//=mybatis의 mapper
@Repository
public interface BookRepository {

    Iterable<Book> findAll();
    Optional<Book> findByIsbn(String isbn);
    Book save(Book book);
    void deleteByIsbn(String isbn);
    
    //책이 있는지 여부 -> 유효성 체크
    boolean existsByIsbn(String isbn);
}
