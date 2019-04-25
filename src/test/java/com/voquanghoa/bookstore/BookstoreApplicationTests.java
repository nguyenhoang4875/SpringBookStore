package com.voquanghoa.bookstore;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableAutoConfiguration(exclude = {org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration.class})
public class BookstoreApplicationTests {

	@Value("${profile.testing}")
	private boolean isTest;

	@Autowired
	private Environment environment;

	@Test
	public void contextLoads() {
		System.out.println("Profile = " + String.join(",",  environment.getActiveProfiles()));
	}

	@Test
	public void test_isTesting(){
		assertTrue(isTest);
	}
}
