package com.acha.books;

import com.acha.books.domain.BookDTO;
import com.acha.books.domain.BookEntity;

import java.awt.print.Book;

public class TestData {
    private TestData() {

    }

    public static BookDTO testBook() {
        return BookDTO.builder().isbn("0123123").author("Jerry Spring").title("Waves").build();
    }

    public static BookEntity testBookEntity() {
        return BookEntity.builder().isbn("0123123").author("Jerry Spring").title("Waves").build();
    }
}
