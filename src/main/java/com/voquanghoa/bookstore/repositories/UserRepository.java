package com.voquanghoa.bookstore.repositories;

import com.voquanghoa.bookstore.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public interface UserRepository extends CrudRepository<User, Integer>{

    User findByUsername(String username);
}
