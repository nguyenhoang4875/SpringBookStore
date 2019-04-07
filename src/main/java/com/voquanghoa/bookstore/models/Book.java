package com.voquanghoa.bookstore.models;

import lombok.Data;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    private String name;

    @ManyToOne
    @Cascade(value= {org.hibernate.annotations.CascadeType.MERGE})
    private Author author;

    private int year;
}
