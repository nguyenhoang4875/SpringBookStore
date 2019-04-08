package com.voquanghoa.bookstore.configurations;

import com.voquanghoa.bookstore.models.Role;
import com.voquanghoa.bookstore.models.User;
import com.voquanghoa.bookstore.repositories.RoleRepository;
import com.voquanghoa.bookstore.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;

import java.util.HashSet;

@Component
public class DataSeedingListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if (roleRepository.findByName("ROLE_ADMIN") == null) {
            roleRepository.save(new Role("ROLE_ADMIN", "Administrators"));
        }

        if (roleRepository.findByName("ROLE_MEMBER") == null) {
            roleRepository.save(new Role("ROLE_MEMBER", "Members"));
        }

        if (userRepository.findByUsername("user") == null) {
            User user = new User("user", "Firstname", "Lastname", new BCryptPasswordEncoder().encode("456"));
            user.setRoles(new HashSet<>());
            user.getRoles().add(roleRepository.findByName("ROLE_MEMBER"));
            userRepository.save(user);
        }

        if (userRepository.findByUsername("admin") == null) {
            User admin = new User("admin", "Vo", "Hoa", new BCryptPasswordEncoder().encode("1234"));
            admin.setRoles(new HashSet<>());
            admin.getRoles().add(roleRepository.findByName("ROLE_ADMIN"));
            admin.getRoles().add(roleRepository.findByName("ROLE_MEMBER"));
            userRepository.save(admin);
        }

        String jws = Jwts.builder().setSubject("BookStore").signWith(SignatureAlgorithm.HS256, "BookStoreApi").compact();

        System.out.println(jws);
    }
}
