package com.voquanghoa.bookstore.repositories;

import com.voquanghoa.bookstore.models.dao.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {

    Iterable<Book> findByNameContaining(String name);

}
