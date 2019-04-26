package com.voquanghoa.bookstore.controllers;

import com.voquanghoa.bookstore.converters.bases.Converter;
import com.voquanghoa.bookstore.exceptions.NotFoundException;
import com.voquanghoa.bookstore.models.dao.Book;
import com.voquanghoa.bookstore.models.dto.BookDTO;
import com.voquanghoa.bookstore.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private Converter<BookDTO, Book> bookDTOBookConverter;

    @Autowired
    private Converter<Book, BookDTO> bookBookDTOConverter;

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/{id}")
    public BookDTO get(@PathVariable int id){
        Optional<Book> optionalBook = bookRepository.findById(id);

        if(optionalBook.isPresent()){
            return bookBookDTOConverter.convert(optionalBook.get());
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
    public Iterable<BookDTO> get(){
        return  bookBookDTOConverter.convert(bookRepository.findAll(Sort.by(Sort.Direction.ASC, "id")));
    }

    @PostMapping()
    public void post(@Valid @RequestBody BookDTO book){
        book.setId(0);
        bookRepository.save(bookDTOBookConverter.convert(book));
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
