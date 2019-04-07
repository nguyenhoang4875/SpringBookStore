package com.voquanghoa.bookstore.repositories;

import com.voquanghoa.bookstore.models.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Integer> {

    Iterable<Book> findByNameContainingOrAuthorContainingAllIgnoreCase(String name, String author);

    Iterable<Book> findByNameContaining(String name);

}
