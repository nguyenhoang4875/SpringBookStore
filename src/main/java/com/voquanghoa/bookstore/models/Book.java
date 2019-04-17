package com.voquanghoa.bookstore.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.springframework.beans.factory.annotation.Required;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @NonNull
    private int id;

    @NonNull
    private String name;

    @ManyToOne
    @Cascade(value= {org.hibernate.annotations.CascadeType.MERGE})
    private Author author;

    private int year;
}
