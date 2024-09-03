package com.djoseffer.tdd.repositories;

import com.djoseffer.tdd.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByNameStartingWith(String name);
}
