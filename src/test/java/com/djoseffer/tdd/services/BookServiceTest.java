package com.djoseffer.tdd.services;

import com.djoseffer.tdd.domain.Book;
import com.djoseffer.tdd.domain.Category;
import com.djoseffer.tdd.domain.Status;
import com.djoseffer.tdd.exceptions.BookNotFoundException;
import com.djoseffer.tdd.repositories.BookRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static com.djoseffer.tdd.BookFactory.createBook;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @Test
    @DisplayName("Success - Should save book with success")
    void shouldSaveBookWithSuccess() {
        when(bookRepository.save(ArgumentMatchers.any(Book.class))).thenReturn(createBook());
        Book created = bookService.createBook(createBook());
        assertThat(created.getName()).isSameAs(createBook().getName());
        assertNotNull(created.getId());
        assertEquals(created.getId(), 1);
    }

    @Test
    @DisplayName("Success - Should return the list of books with success")
    void shouldReturnListOfBooksWithSuccess() {
        when(bookRepository.findAll()).thenReturn(List.of(createBook()));
        List<Book> books = bookService.listAllBooks();
        assertEquals(1, books.size());
    }

    @Test
    @DisplayName("Success- Should find a book by id with success")
    void shouldFindBookByIdWithSuccess() throws BookNotFoundException {
        var book = new Book();
        book.setId(1L);
        book.setName("cracking the code interview");
        book.setCategory(Category.ARCHITECTURE);
        book.setStatus(Status.NOT_STARTED);

        when(bookRepository.findById(book.getId())).thenReturn(Optional.of(createBook()));
        Optional<Book> expected = bookService.getBookById(book.getId());
        assertThat(expected.get().getId()).isEqualTo(book.getId());
        assertThat(expected.get().getName()).isEqualTo(book.getName());
        assertThat(expected.get().getCategory()).isEqualTo(book.getCategory());
        assertThat(expected.get().getStatus()).isEqualTo(book.getStatus());
        assertDoesNotThrow(() -> {
                    bookService.getBookById(book.getId());
                }
        );

    }

    @Test
    @DisplayName("Error - Should throw exception when try to find book id")
    void shouldThrowExceptionWhenTryToFindBookId() throws BookNotFoundException {

        var book = new Book();
        book.setId(1L);
        book.setName("cracking the code interview");
        book.setCategory(Category.PROGRAMMING);
        book.setStatus(Status.NOT_STARTED);

        when(bookRepository.findById(200L)).thenReturn(Optional.empty());
        Exception exception = assertThrows(BookNotFoundException.class,
                () -> bookService.getBookById(200L));
                assertEquals("Book with id " + 200L + "not found ", exception.getMessage());
    }

}
