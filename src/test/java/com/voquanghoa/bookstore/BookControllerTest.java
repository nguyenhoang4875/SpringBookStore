package com.voquanghoa.bookstore;

import com.google.gson.Gson;
import com.voquanghoa.bookstore.models.dao.Book;
import com.voquanghoa.bookstore.models.dto.BookDTO;
import com.voquanghoa.bookstore.repositories.BookRepository;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WebAppConfiguration
@EnableAutoConfiguration(exclude = {org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration.class})
public class BookControllerTest {

    @Autowired
    private BookRepository bookRepository;

    private Book book1;
    private Book book2;

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void init(){

        book1 = new Book("English");
        book1.setYear(2000);

        book1 = bookRepository.save(book1);

        book2 = new Book("Mathematics");
        book2.setYear(2001);
        book2 = bookRepository.save(book2);
    }

    @After
    public void destroy(){
        bookRepository.deleteAll();
    }

    @Test
    public void test_getAllBook() throws Exception{
        mockMvc.perform(get("/api/books"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", Matchers.hasSize(2)))
                .andExpect(jsonPath("$[0].id", Matchers.equalTo(book1.getId())))
                .andExpect(jsonPath("$[0].name", Matchers.equalTo("English")))
                .andExpect(jsonPath("$[1].id", Matchers.equalTo(book2.getId())))
                .andExpect(jsonPath("$[1].name", Matchers.equalTo("Mathematics")));
    }

    @Test
    public void test_getBook_Found() throws Exception{
        mockMvc.perform(get("/api/books/" + book2.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", Matchers.equalTo(book2.getId())))
                .andExpect(jsonPath("$.name", Matchers.equalTo(book2.getName())));
    }

    @Test
    public void test_getBook_NotFound() throws Exception{
        mockMvc.perform(get("/api/books/" + (book2.getId() + book1.getId())))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void test_deleteBook_NotFound() throws Exception{
        mockMvc.perform(delete("/api/books/" + (book2.getId() + book1.getId())))
                .andExpect(status().isNotFound());
    }

    @Test
    public void test_deleteBook_Found() throws Exception{
        mockMvc.perform(delete("/api/books/" + book1.getId()))
                .andExpect(status().isOk());

        assertFalse(bookRepository.findById( book1.getId()).isPresent());
    }

    @Test
    public void test_put_Found() throws Exception{

        Gson gson = new Gson();

        BookDTO putBook = new BookDTO();
        putBook.setName("Math");
        putBook.setId(book2.getId());
        putBook.setYear(2001);
        String json = gson.toJson(putBook);


        mockMvc.perform(put("/api/books")
                .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk());

        Optional<Book> book = bookRepository.findById(book2.getId());

        assertTrue(book.isPresent());
        assertEquals(book.get().getName(), "Math");
    }

    @Test
    public void test_post_ok() throws Exception{

        Gson gson = new Gson();
        BookDTO bookPost = new BookDTO();
        bookPost.setYear(2001);
        bookPost.setName("Geometry");

        String json = gson.toJson(bookPost);

        mockMvc.perform(put("/api/books")
                .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk());


        ArrayList<Book> books = (ArrayList<Book>) bookRepository.findAll();
        Book book = books.get(books.size()-1);

        assertEquals (book.getName(), "Geometry");
    }

    @Test
    public void test_post_not_ok() throws Exception{

        Gson gson = new Gson();
        BookDTO bookPost = new BookDTO();


        mockMvc.perform(post("/api/books")
                .contentType(MediaType.APPLICATION_JSON).content(gson.toJson(bookPost)))
                .andExpect(status().isBadRequest());

        bookPost.setName("Math");
        mockMvc.perform(post("/api/books")
                .contentType(MediaType.APPLICATION_JSON).content(gson.toJson(bookPost)))
                .andExpect(status().isBadRequest());

        bookPost.setYear(1900);
        mockMvc.perform(post("/api/books")
                .contentType(MediaType.APPLICATION_JSON).content(gson.toJson(bookPost)))
                .andExpect(status().isBadRequest());

        bookPost.setYear(2101);
        mockMvc.perform(post("/api/books")
                .contentType(MediaType.APPLICATION_JSON).content(gson.toJson(bookPost)))
                .andExpect(status().isBadRequest());
    }
}
