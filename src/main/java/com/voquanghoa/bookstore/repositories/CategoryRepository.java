package com.voquanghoa.bookstore.repositories;

import com.voquanghoa.bookstore.models.dao.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Integer> {
}
