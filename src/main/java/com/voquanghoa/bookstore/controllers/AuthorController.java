package com.voquanghoa.bookstore.controllers;

import com.voquanghoa.bookstore.models.Author;
import com.voquanghoa.bookstore.models.Book;
import com.voquanghoa.bookstore.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    @Autowired
    private AuthorRepository authorRepository;

    @GetMapping("/{id}")
    Optional<Author> get(@PathVariable int id){
        return authorRepository.findById(id);
    }

    @GetMapping
    void get(@RequestParam String name){
        //authorRepository.deleteByEmail(name);
        authorRepository.updateEmailById(2, "quanghoa@gmail.com");
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable int id){
        authorRepository.deleteById(id);
    }

    @PostMapping()
    void post(@RequestBody Author author){
        author.setId(0);
        authorRepository.save(author);
    }

    @PutMapping()
    void put(@RequestBody Author author){
        authorRepository.save(author);
    }
}
