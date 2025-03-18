package com.example.catalogservice.domain;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

//=mybatis의 mapper
//jdbc에서 save, findall은 기본적으로 제공
@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
    
    Optional<Book> findByIsbn(String isbn);
    @Modifying
    @Transactional
    @Query ("DELETE FROM book WHERE isbn:isbn")
    void deleteByIsbn(String isbn);
    boolean existsByIsbn(String isbn);
}
