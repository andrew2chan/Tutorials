package com.acha.books.controllers;

import com.acha.books.domain.BookDTO;
import com.acha.books.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BookController {
    private BookService bookService;

    @Autowired
    public BookController(final BookService bookService) {
        this.bookService = bookService;
    }

    @PutMapping(path = "/book/{isbn}")
    public ResponseEntity<BookDTO> createUpdateBook(@RequestBody BookDTO book, @PathVariable final String isbn) {
        book.setIsbn(isbn);
        final BookDTO savedBook = bookService.save(book);
        boolean isBookExists = bookService.isBookExists(book);

        if(isBookExists) {
            return new ResponseEntity<BookDTO>(savedBook, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<BookDTO>(savedBook, HttpStatus.CREATED);
        }
    }

    @GetMapping(path = "/book/{isbn}")
    public ResponseEntity<BookDTO> retrieveBook(@PathVariable final String isbn) {
        Optional<BookDTO> foundBook = bookService.findById(isbn);

        return foundBook.map(book -> new ResponseEntity<BookDTO>(book, HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping(path = "/books")
    public ResponseEntity<List<BookDTO>> listBooks() {
        return new ResponseEntity<List<BookDTO>>(bookService.listBooks(), HttpStatus.OK);
    }

    @DeleteMapping(path = "/book/{isbn}")
    public ResponseEntity deleteBook(@PathVariable final String isbn) {
        bookService.deleteBookById(isbn);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
