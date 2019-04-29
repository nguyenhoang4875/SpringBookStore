package com.voquanghoa.bookstore.converters;

import com.voquanghoa.bookstore.converters.bases.Converter;
import com.voquanghoa.bookstore.exceptions.BadRequestException;
import com.voquanghoa.bookstore.models.dao.Author;
import com.voquanghoa.bookstore.models.dao.Book;
import com.voquanghoa.bookstore.models.dto.BookDTO;
import com.voquanghoa.bookstore.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class BookDtoToBookDaoConverter extends Converter<BookDTO, Book> {

    @Autowired
    private AuthorRepository authorRepository;


    @Override
    public Book convert(BookDTO source) {

        Book book = new Book();

        book.setYear(source.getYear());
        book.setId(source.getId());
        book.setName(source.getName());

        if(source.getAuthorId() > 0){
            Optional<Author> author = authorRepository.findById(source.getAuthorId());

            if(author.isPresent()){
                book.setAuthor(author.get());
            }else{
                throw new BadRequestException("Invalid author id " + source.getAuthorId());
            }
        }

        return book;
    }
}
