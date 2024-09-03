package com.djoseffer.tdd.services;

import com.djoseffer.tdd.domain.Book;
import com.djoseffer.tdd.exceptions.BookNotFoundException;
import com.djoseffer.tdd.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    public List<Book> listAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> getBookById(Long id) throws BookNotFoundException {
        if (bookRepository.findById(id).isEmpty()) {
            throw new BookNotFoundException(id);
        }
        return bookRepository.findById(id);
    }

    public ResponseEntity<Book> updateBook(Book book, Long id) throws BookNotFoundException {
        return bookRepository.findById(id).map(
                bookToUpdate -> {
                    bookToUpdate.setName(book.getName());
                    bookToUpdate.setStatus(book.getStatus());
                    bookToUpdate.setCategory(book.getCategory());
                    bookRepository.save(bookToUpdate);
                    return ResponseEntity.ok(bookToUpdate);
                }).orElse(ResponseEntity.notFound().build());
    }
}
