package com.voquanghoa.bookstore;

import com.voquanghoa.bookstore.models.User;
import com.voquanghoa.bookstore.repositories.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void test_findByUsername(){
        userRepository.save(new User("abc", "First name", "Last name", "123"));

        assertNotNull(userRepository.findByUsername("abc"));
        assertNull(userRepository.findByUsername("ffff"));
    }
}
