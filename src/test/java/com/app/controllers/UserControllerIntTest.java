package com.app.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import com.app.AppApplication;
import com.app.dto.UserResponse;
import com.app.entities.User;
import com.app.repositories.UserRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = AppApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:app_int_test.properties")
//@AutoConfigureTestDatabase

public class UserControllerIntTest {


    @Autowired
    private MockMvc mvc;

    @Autowired
    private UserRepository repository;
    private long id = 1;

    @Before
    public void initDB() {
        //createTestUser(id,"F"+id,"L"+id,Date.valueOf("200"+id+"-01-01"));
    }

    @After
    public void resetDB() {
        repository.deleteAll();
    }

    @Test
    public void givenUserById_thenStatus200() throws Exception {
        User u = createTestUser(id, "name_", "name_", Date.valueOf("200" + id + "-01-01"));
        mvc.perform(get("/user/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.firstName", is(u.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(u.getLastName())))
                .andExpect(jsonPath("$.age", is(UserResponse.ageCalculator(u.getDateOfBirth()))))
                .andExpect(jsonPath("$").isNotEmpty());
    }

    private User createTestUser(Long id, String firstName, String lastName, Date dateOfBirth) {
        return repository.saveAndFlush(
                new User(id, firstName, lastName, dateOfBirth)
        );
    }
}