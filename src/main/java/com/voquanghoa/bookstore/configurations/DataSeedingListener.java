package com.voquanghoa.bookstore.configurations;

import com.voquanghoa.bookstore.models.Role;
import com.voquanghoa.bookstore.models.User;
import com.voquanghoa.bookstore.repositories.RoleRepository;
import com.voquanghoa.bookstore.repositories.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
@Configuration
@Profile({"!test"})
public class DataSeedingListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Value("${jwt-key}")
    private String signingKey;

    @Value("${profile-name}")
    private String profileName;


    private void addRoleIfMissing(String name, String description){
        if (roleRepository.findByName(name) == null) {
            roleRepository.save(new Role(name, description));
        }
    }

    private void addUserIfMissing(String username, String password, String... roles){
        if (userRepository.findByUsername(username) == null) {
            User user = new User(username, "First name", "Last name", new BCryptPasswordEncoder().encode(password));
            user.setRoles(new HashSet<>());

            for (String role: roles) {
                user.getRoles().add(roleRepository.findByName(role));
            }

            userRepository.save(user);
        }
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        addRoleIfMissing("ROLE_ADMIN", "Administrators");
        addRoleIfMissing("ROLE_MEMBER", "Members");

        addUserIfMissing("user", "456", "ROLE_MEMBER");
        addUserIfMissing("admin", "1234", "ROLE_MEMBER", "ROLE_ADMIN");

        if(signingKey == null || signingKey.length() ==0){
            String jws = Jwts.builder()
                    .setSubject("BookStore")
                    .signWith(SignatureAlgorithm.HS256, "BookStoreApi").compact();

            System.out.println("Use this jwt key:");
            System.out.println("jwt-key=" + jws);
        }

        System.out.println("Key = " + profileName);
    }
}
