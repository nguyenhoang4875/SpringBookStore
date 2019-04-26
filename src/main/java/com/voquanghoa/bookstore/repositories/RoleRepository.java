package com.voquanghoa.bookstore.repositories;

import com.voquanghoa.bookstore.models.dao.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Integer> {

    Role findByName(String name);
}
