package com.voquanghoa.bookstore.repositories;

import com.voquanghoa.bookstore.models.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Integer> {
}
