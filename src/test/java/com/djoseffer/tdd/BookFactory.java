package com.djoseffer.tdd;

import com.djoseffer.tdd.domain.Book;
import com.djoseffer.tdd.domain.Category;
import com.djoseffer.tdd.domain.Status;

public class BookFactory {

    public static Book createBook() {
        var book = new Book();
        book.setId(1L);
        book.setName("cracking the code interview");
        book.setCategory(Category.ARCHITECTURE);
        book.setStatus(Status.NOT_STARTED);
        return book;
    }
}
