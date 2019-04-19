package com.voquanghoa.bookstore;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableAutoConfiguration(exclude = {org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration.class})
public class BookstoreApplicationTests {

	@Value("${spring.datasource.url}")
	private String dataSourceUrl;

	@Test
	public void contextLoads() {
	}

	@Test
	public void test_dataSourceUrl(){
		assertEquals(dataSourceUrl, "jdbc:mysql://localhost:3306/bookstore-test");
	}
}
