package com.voquanghoa.bookstore.converters;

import com.voquanghoa.bookstore.converters.bases.Converter;
import com.voquanghoa.bookstore.exceptions.BadRequestException;
import com.voquanghoa.bookstore.models.dao.Book;
import com.voquanghoa.bookstore.models.dto.BookDTO;
import org.springframework.stereotype.Component;

@Component
public class BookDaoToBookDtoConverter extends Converter<Book, BookDTO> {

    @Override
    public BookDTO convert(Book source) throws BadRequestException {

        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(source.getId());
        bookDTO.setName(source.getName());
        bookDTO.setYear(source.getYear());

        if(source.getAuthor() != null){
            bookDTO.setAuthorId(source.getAuthor().getId());
        }

        return bookDTO;
    }
}
