package com.voquanghoa.bookstore.models.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;

@Data
public class BookDTO {
    int id;

    @Length(min=5, max=1024)
    String name;


    int authorId;

    @Min(1990)
    int year;
}
