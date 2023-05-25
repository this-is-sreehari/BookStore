package com.weCode.bookStore.service;

import com.weCode.bookStore.Dto.BookDto;
import com.weCode.bookStore.Model.Book;
import com.weCode.bookStore.Repository.BookRepository;
import com.weCode.bookStore.Service.BookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {
    @InjectMocks
    private BookService bookService;
    @Mock
    private BookRepository bookRepository;

    @Mock
    private ModelMapper mapper;

    @Test
    void shouldReturnListOfBookDtoWhenGetBooksCalled(){
        List<Book> books = new ArrayList<>();
        Book book = getBook();
        BookDto bookDto = getBookDto();
        books.add(book);
        when(bookRepository.findAll()).thenReturn(books);
        when(mapper.map(book, BookDto.class)).thenReturn(bookDto);
        List<BookDto> bookDtos = bookService.getBooks();
        assertThat(1).isEqualTo(bookDtos.size());
        assertThat(bookDtos.get(0))
                .isNotNull()
                .hasFieldOrPropertyWithValue("title","Test Title")
                .hasFieldOrPropertyWithValue("description","test description")
                .hasFieldOrPropertyWithValue("releaseYear",2020);
    }

    private Book getBook(){
        return Book.builder()
                .title("Test Title")
                .description("test description")
                .id(UUID.randomUUID())
                .releaseYear(2020)
                .build();
    }

    private BookDto getBookDto(){
        return BookDto.builder()
                .title("Test Title")
                .description("test description")
                .id(UUID.randomUUID())
                .releaseYear(2020)
                .build();
    }
}
