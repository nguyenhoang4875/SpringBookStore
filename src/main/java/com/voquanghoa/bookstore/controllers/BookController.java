package com.voquanghoa.bookstore.controllers;

import com.voquanghoa.bookstore.exceptions.NotFoundException;
import com.voquanghoa.bookstore.models.Book;
import com.voquanghoa.bookstore.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/{id}")
    public Book get(@PathVariable int id){
        Optional<Book> optionalBook = bookRepository.findById(id);

        if(optionalBook.isPresent()){
            return optionalBook.get();
        }

        throw new NotFoundException(String.format("Book id %d not found", id));
    }


    @DeleteMapping("/{id}")
    void delete(@PathVariable int id){

        if(!bookRepository.existsById(id)){
            throw new NotFoundException(String.format("Book id %d not found", id));
        }

        bookRepository.deleteById(id);
    }

    @GetMapping
    public Iterable<Book> get(){
        return  bookRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    @PostMapping()
    public void post(@RequestBody Book book){
        book.setId(0);
        bookRepository.save(book);
    }

    @GetMapping("/find")
    public Iterable<Book> find(@RequestParam String name){
        return bookRepository.findByNameContaining(name);
    }

    @PutMapping()
    public void put(@RequestBody Book book){
        bookRepository.save(book);
    }
}
