package com.voquanghoa.bookstore.controllers;

import com.voquanghoa.bookstore.models.dao.Category;
import com.voquanghoa.bookstore.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/{id}")
    Optional<Category> get(@PathVariable int id){
        return categoryRepository.findById(id);
    }

    @GetMapping
    Iterable<Category> get(){
        return categoryRepository.findAll();
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable int id){
        categoryRepository.deleteById(id);
    }

    @PostMapping()
    void post(@RequestBody Category category){
        category.setId(0);
        categoryRepository.save(category);
    }

    @PutMapping()
    void put(@RequestBody Category category){
        categoryRepository.save(category);
    }

}
