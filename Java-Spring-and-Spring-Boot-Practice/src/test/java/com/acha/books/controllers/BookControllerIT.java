package com.acha.books.controllers;

import com.acha.books.domain.BookDTO;
import com.acha.books.domain.BookEntity;
import com.acha.books.services.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static com.acha.books.TestData.testBook;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BookControllerIT {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookService bookService;

    @Test
    public void testThatBookIsCreatedReturns200() throws Exception {
        final BookDTO book = testBook();
        final ObjectMapper objectMapper = new ObjectMapper();
        final String bookJSON = objectMapper.writeValueAsString(book);

        mockMvc.perform(MockMvcRequestBuilders.put("/book/" + book.getIsbn())
                .contentType(MediaType.APPLICATION_JSON)
                .content(bookJSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.isbn").value(book.getIsbn()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.author").value(book.getAuthor()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value(book.getTitle()));
    }

    @Test
    public void testThatBookIsCreatedReturns201() throws Exception {
        final BookDTO book = testBook();
        bookService.save(book);

        book.setAuthor("Birgin Wolf");

        final ObjectMapper objectMapper = new ObjectMapper();
        final String bookJSON = objectMapper.writeValueAsString(book);

        mockMvc.perform(MockMvcRequestBuilders.put("/book/" + book.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bookJSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.isbn").value(book.getIsbn()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.author").value(book.getAuthor()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value(book.getTitle()));
    }

    @Test
    public void testThatRetrievedBookReturns404WhenBookNotFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/book/1"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testThatRetrievedBookReturns200WhenBookFound() throws Exception {
        final BookDTO book = testBook();
        bookService.save(book);

        mockMvc.perform(MockMvcRequestBuilders.get("/book/" + book.getIsbn()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.isbn").value(book.getIsbn()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.author").value(book.getAuthor()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value(book.getTitle()));
    }

    @Test
    public void testThatListBooksReturns200EmptyListWhenNoBooksExist() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/books"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    public void testThatListBooksReturns200AndBooksWhenBooksExist() throws Exception {
        final BookDTO book = testBook();
        bookService.save(book);

        mockMvc.perform(MockMvcRequestBuilders.get("/books"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].isbn").value(book.getIsbn()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].author").value(book.getAuthor()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].title").value(book.getTitle()));
    }

    @Test
    public void testThat204IsReturnedWhenBookDoesntExist() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/book/34234234"))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    public void testThat204IsReturnedWhenBookIsDeleted() throws Exception {
        final BookDTO book = testBook();
        bookService.save(book);

        mockMvc.perform(MockMvcRequestBuilders.delete("/book/" + book.getIsbn()))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    public void testThatBookIsActuallyDeleted() throws Exception {
        final BookDTO book = testBook();
        bookService.save(book);
        bookService.deleteBookById(book.getIsbn());

        List<BookDTO> foundBooks = bookService.listBooks();
        assertEquals(foundBooks.size(), 0);
    }
}
