package com.acha.books.services.impl;

import com.acha.books.domain.BookDTO;
import com.acha.books.domain.BookEntity;
import com.acha.books.respository.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;
import java.util.Optional;

import static com.acha.books.TestData.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BookServiceImplTest {
    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookServiceImpl underTest;

    @Test
    public void testThatBookIsSaved() {
        final BookDTO book = testBook();
        final BookEntity bookEntity = testBookEntity();

        when(bookRepository.save(eq(bookEntity))).thenReturn(bookEntity);

        final BookDTO result = underTest.save(book);
        assertThat(book).isEqualTo(result);
    }

    @Test
    public void testFindByIdReturnsEntityWhenNoBook() {
        final String isbn = "21312312312312";

        when(bookRepository.findById(eq(isbn))).thenReturn(Optional.empty());

        final Optional<BookDTO> result = underTest.findById((isbn));
        assertEquals(Optional.empty(), result);
    }

    @Test
    public void testThatFindByIdReturnsBookWhenExists() {
        final BookDTO book = testBook();
        final BookEntity bookEntity = testBookEntity();

        when(bookRepository.findById(eq(book.getIsbn()))).thenReturn((Optional.of(bookEntity)));

        final Optional<BookDTO> result = underTest.findById(book.getIsbn());
        assertEquals(Optional.of(book), result);
    }

    @Test
    public void testListBooksReturnsEmptyListWhenNoBooksExist() {
        final List<BookDTO> result = underTest.listBooks();
        assertEquals(0, result.size());
    }

    @Test
    public void testListBooksReturnsBookWhenExists() {
        final BookEntity bookEntity = testBookEntity();
        when(bookRepository.findAll()).thenReturn(List.of(bookEntity));
        final List<BookDTO> result = underTest.listBooks();

        assertEquals(1, result.size());
    }

    @Test
    public void testBookExistsReturnsFalseWhenBookDoesntExist() throws Exception {
        when(bookRepository.existsById(any())).thenReturn((false));
        boolean result = underTest.isBookExists(testBook());
        assertEquals(false, result);
    }

    @Test
    public void testBookExistsReturnsTrueWhenBookDoesntExist() throws Exception {
        when(bookRepository.existsById(any())).thenReturn((true));
        boolean result = underTest.isBookExists(testBook());
        assertEquals(true, result);
    }

    @Test
    public void testDeleteBookDeletesBook() {
        final String isbn = "23123";
        underTest.deleteBookById(isbn);
        verify(bookRepository, times(1)).deleteById((eq(isbn)));
    }
}
