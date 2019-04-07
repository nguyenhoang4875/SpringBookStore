package com.voquanghoa.bookstore.repositories;

import com.voquanghoa.bookstore.models.Author;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface AuthorRepository extends CrudRepository<Author, Integer> {

    @Transactional
    @Modifying
    @Query("delete Author where email = :emailAddress")
    void deleteByEmail(String emailAddress);

    @Transactional
    @Modifying
    @Query(value = "update Author set email = :newEmail  where id = :id")
    void updateEmailById(int id, String newEmail);
}
