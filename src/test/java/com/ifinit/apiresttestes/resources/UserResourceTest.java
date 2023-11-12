package com.ifinit.apiresttestes.resources;

import com.ifinit.apiresttestes.domain.User;
import com.ifinit.apiresttestes.domain.dto.UserDTO;
import com.ifinit.apiresttestes.services.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
@SpringBootTest
class UserResourceTest {

    private static final Integer ID = 1;
    private static final String NAME = "Saulo";
    private static final String MAIL = "saulo@mail.com";
    private static final String PASSWORD = "123";

    private  User user;
    private UserDTO userDTO;
    @InjectMocks
    private UserResource resource;

    private UserServiceImpl service;

    private ModelMapper mapper;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUser();
    }

    @Test
    void findById() {
    }

    @Test
    void findAll() {
    }

    @Test
    void update() {
    }

    @Test
    void create() {
    }

    @Test
    void delete() {
    }

    private void startUser(){
        user = new User(ID, NAME, MAIL, PASSWORD);
        userDTO = new UserDTO(ID, NAME, MAIL, PASSWORD);

    }
}