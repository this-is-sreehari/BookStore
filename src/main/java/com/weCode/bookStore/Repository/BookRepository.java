package com.weCode.bookStore.Repository;

import com.weCode.bookStore.Model.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface BookRepository extends CrudRepository<Book, UUID> {
    List<Book> findBooksByTitle (String title);
}
