package com.ifinit.apiresttestes.services.impl;

import com.ifinit.apiresttestes.domain.User;
import com.ifinit.apiresttestes.domain.dto.UserDTO;
import com.ifinit.apiresttestes.repositories.UserRepository;
import com.ifinit.apiresttestes.services.exceptions.DataIntegratyViolationException;
import com.ifinit.apiresttestes.services.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceImplTest {

    public static final Integer ID = 1;
    public static final String NAME = "Saulo";
    public static final String MAIL = "saulo@mail.com";
    public static final String PASSWORD = "123";
    public static final int INDEX = 0;
    public static final String OBJECT_NOT_FOUND = "Object not found.";
    public static final String THIS_EMAIL_IS_ALREADY_REGISTERED_IN_THE_SYSTEM = "This email is already registered in the system.";
    @InjectMocks
    private UserServiceImpl service;
    @Mock
    private UserRepository repository;
    @Mock
    private ModelMapper mapper;
    private User user;
    private UserDTO userDTO;
    private Optional<User> optionalUser;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUser();
    }

    @Test
    void whenFindByIdThenReturnAnUserInstance() {
        when(repository.findById(Mockito.anyInt())).thenReturn(optionalUser);
        User response = service.findById(ID);
        assertNotNull(response);
        assertEquals(User.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(MAIL, response.getMail());
        assertEquals(PASSWORD, response.getPassword());

    }
    @Test
    void whenFindByIdThenReturnObjectNotFoundException(){
    when(repository.findById(Mockito.anyInt())).thenThrow(new ObjectNotFoundException(OBJECT_NOT_FOUND));
    try {
        service.findById(ID);
    }catch (Exception ex){
        assertEquals(ObjectNotFoundException.class,
                ex.getClass());
        assertEquals(OBJECT_NOT_FOUND, ex.getMessage());
    }
    }

    @Test
    void whenFindAllThenReturnAnListAllUsers() {
    when(repository.findAll()).thenReturn(List.of(user));
    List<User> response = service.findAll();

    assertNotNull(response);
    assertEquals(1, response.size());
    assertEquals(User.class, response.get(INDEX).getClass());

    assertEquals(ID, response.get(INDEX).getId());
    assertEquals(NAME, response.get(INDEX).getName());
    assertEquals(MAIL, response.get(INDEX).getMail());
    assertEquals(PASSWORD, response.get(INDEX).getPassword());
    }

    @Test
    void whenCreateThenReturnSuccess() {
         when(repository.save(any())).thenReturn(user);

         User response = service.create(userDTO);

         assertNotNull(response);
         assertEquals(User.class, response.getClass());
         assertEquals(ID, response.getId());
         assertEquals(NAME, response.getName());
         assertEquals(MAIL, response.getMail());
         assertEquals(PASSWORD, response.getPassword());

    }

    @Test
    void whenCreateThenReturnAnDataIntegrityViolationException() {
        when(repository.findByMail(anyString())).thenReturn(optionalUser);

        try{
            optionalUser.get().setId(2);
            service.create(userDTO);
        } catch (Exception ex){
            assertEquals(DataIntegratyViolationException.class, ex.getClass());

            assertEquals(THIS_EMAIL_IS_ALREADY_REGISTERED_IN_THE_SYSTEM, ex.getMessage());
        }


    }

    @Test
    void whenUpdateThenReturnSuccess() {
        when(repository.save(any())).thenReturn(user);

        User response = service.update(userDTO);

        assertNotNull(response);
        assertEquals(User.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(MAIL, response.getMail());
        assertEquals(PASSWORD, response.getPassword());

    }


    @Test
    void whenUpdateThenReturnAnDataIntegrityViolationException() {
        when(repository.findByMail(anyString())).thenReturn(optionalUser);

        try{
            optionalUser.get().setId(2);
            service.update(userDTO);
        } catch (Exception ex){
            assertEquals(DataIntegratyViolationException.class, ex.getClass());

            assertEquals(THIS_EMAIL_IS_ALREADY_REGISTERED_IN_THE_SYSTEM, ex.getMessage());
        }


    }

    @Test
    void delete() {
    }

    private void startUser(){
        user = new User(ID, NAME, MAIL, PASSWORD);
        userDTO = new UserDTO(ID, NAME, MAIL, PASSWORD);
        optionalUser =  Optional.of(new User(ID, NAME, MAIL, PASSWORD));
    }

}