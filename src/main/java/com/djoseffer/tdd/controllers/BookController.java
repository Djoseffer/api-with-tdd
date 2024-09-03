package com.djoseffer.tdd.controllers;

import com.djoseffer.tdd.domain.Book;
import com.djoseffer.tdd.exceptions.BookNotFoundException;
import com.djoseffer.tdd.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book createBook(@RequestBody Book book) {
        return bookService.createBook(book);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Book> listAllBooks() {
        return bookService.listAllBooks();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Book> getBookById(@PathVariable Long id) throws BookNotFoundException {
        return bookService.getBookById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Book> updateBookById(@RequestBody Book book, @PathVariable Long id) throws BookNotFoundException {
        return bookService.updateBook(book, id);
    }
}
