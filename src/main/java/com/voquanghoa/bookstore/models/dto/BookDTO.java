package com.voquanghoa.bookstore.models.dto;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
public class BookDTO {
    int id;

    @NotBlank(message = "Name is mandatory")
    String name;


    int authorId;

    @Min(value = 1990, message = "Invalid year")
    @Max(value = 2100, message = "Invalid year")
    int year;
}
