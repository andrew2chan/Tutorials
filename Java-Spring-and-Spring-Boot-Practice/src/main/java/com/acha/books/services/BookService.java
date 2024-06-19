package com.acha.books.services;

import com.acha.books.domain.BookDTO;

import java.util.List;
import java.util.Optional;

public interface BookService {
    boolean isBookExists(BookDTO book);
    BookDTO save(BookDTO book);
    Optional<BookDTO> findById(String isbn);
    List<BookDTO> listBooks();
    void deleteBookById(String isbn);
}
