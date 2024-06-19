package com.acha.books.services.impl;

import com.acha.books.domain.BookDTO;
import com.acha.books.domain.BookEntity;
import com.acha.books.respository.BookRepository;
import com.acha.books.services.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(final BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public boolean isBookExists(BookDTO book) {
        return bookRepository.existsById(book.getIsbn());
    }

    @Override
    public BookDTO save(final BookDTO book) {
        final BookEntity bookEntity = bookToBookEntity(book);
        final BookEntity savedBook = bookRepository.save(bookEntity);
        return bookEntityToBook(savedBook);
    }

    @Override
    public Optional<BookDTO> findById(String isbn) {
        final Optional<BookEntity> foundBook = bookRepository.findById(isbn);
        return foundBook.map(book -> bookEntityToBook(book));
    }

    @Override
    public List<BookDTO> listBooks() {
        List<BookEntity> foundBooks = bookRepository.findAll();
        return foundBooks.stream().map(book -> bookEntityToBook(book)).collect(Collectors.toList());
    }

    @Override
    public void deleteBookById(String isbn) {
        try {
            bookRepository.deleteById(isbn);
        } catch (EmptyResultDataAccessException e) {
            log.debug("Attempted to delete non existing book", e);
        }
    }

    private BookEntity bookToBookEntity(BookDTO book) {
        return BookEntity.builder()
                .isbn(book.getIsbn())
                .title(book.getTitle())
                .author(book.getAuthor())
                .build();
    }

    private BookDTO bookEntityToBook(BookEntity bookEntity) {
        return BookDTO.builder()
                .isbn(bookEntity.getIsbn())
                .title(bookEntity.getTitle())
                .author(bookEntity.getAuthor())
                .build();
    }
}
