package com.voquanghoa.bookstore.models;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id = 0;


    @Column(nullable = false)
    @NonNull
    private String name;


    @Column
    @NonNull
    private String description;
}
