package com.weCode.bookStore.repository;

import com.weCode.bookStore.Model.Book;
import com.weCode.bookStore.Repository.BookRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.stream.StreamSupport;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class BookRepositoryTest {
    @Autowired
    private BookRepository bookRepository;

    @Test
    @Sql(scripts = {"classpath:InsertInitialBookRecordForTest.sql"})
    void shouldBeAbleToFetchAllBooksInDB() {
       Iterable<Book> all = bookRepository.findAll();
       Long totalBookCount =  StreamSupport.stream(all.spliterator(),false).count();
        Assertions.assertEquals(totalBookCount,2);
    }

    @Test
    @Sql(scripts = {"classpath:InsertInitialBookRecordForTest.sql"})
    void shouldReturnOneBookWhenTitleIsTestTile(){
        List<Book> testTitle = bookRepository.findBooksByTitle("Test Title");
        Assertions.assertEquals(testTitle.size(),1);

    }
}
