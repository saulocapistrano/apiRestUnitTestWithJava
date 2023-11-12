package com.ifinit.apiresttestes.resources;

import com.ifinit.apiresttestes.domain.User;
import com.ifinit.apiresttestes.domain.dto.UserDTO;
import com.ifinit.apiresttestes.services.impl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserResourceTest {

    public static final Integer ID = 1;
    public static final String NAME = "Saulo";
    public static final String MAIL = "saulo@mail.com";
    public static final String PASSWORD = "123";
    public static final Integer INDEX = 0;


    private User user;
    private UserDTO userDTO;
    @InjectMocks
    private UserResource resource;

    @Mock
    private UserServiceImpl service;

    @Mock
    private ModelMapper mapper;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUser();

    }

    @Test
    void whenFindByIdThenReturnSuccess() {
        when(service.findById(Mockito.anyInt())).thenReturn(user);
        when(mapper.map(Mockito.any(), Mockito.any())).thenReturn(userDTO);

        ResponseEntity<UserDTO> response = resource.findById(ID);

        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getBody());

        Assertions.assertEquals(ResponseEntity.class, response.getClass());
        Assertions.assertEquals(UserDTO.class, response.getBody().getClass());

        Assertions.assertEquals(ID, response.getBody().getId());
        Assertions.assertEquals(NAME, response.getBody().getName());
        Assertions.assertEquals(MAIL, response.getBody().getMail());
        Assertions.assertEquals(PASSWORD, response.getBody().getPassword());

    }

    @Test
    void whenFindAllThenReturnAListOfUserDTO() {
    when(service.findAll()).thenReturn(List.of(user));
    when(mapper.map(any(), any())).thenReturn(userDTO);

    ResponseEntity<List<UserDTO>> response = resource.findAll();

    assertNotNull(response);
    assertNotNull(response.getBody());

    Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    Assertions.assertEquals(ResponseEntity.class, response.getClass());
    Assertions.assertEquals(ArrayList.class, response.getBody().getClass());
    Assertions.assertEquals(UserDTO.class, response.getBody().get(INDEX).getClass());

    Assertions.assertEquals(ID, response.getBody().get(INDEX).getId());
    Assertions.assertEquals(NAME, response.getBody().get(INDEX).getName());
    Assertions.assertEquals(MAIL, response.getBody().get(INDEX).getMail());
    Assertions.assertEquals(PASSWORD, response.getBody().get(INDEX).getPassword());

    }



    @Test
    void whenCreateThenReturnCreated() {
    when(service.create(any())).thenReturn(user);

        ResponseEntity<List<UserDTO>> response = resource.create(userDTO);

        Assertions.assertEquals(ResponseEntity.class, response.getClass());
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Assertions.assertNotNull(response.getHeaders().get("Location"));


    }

    @Test
    void whenUpdateThenReturnSucces() {
        when(service.update(userDTO)).thenReturn(user);
        when(mapper.map(any(), any())).thenReturn(userDTO);

        ResponseEntity<UserDTO> response = resource.update(ID, userDTO);
        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(ResponseEntity.class, response.getClass());
        Assertions.assertEquals(UserDTO.class, response.getBody().getClass());

        Assertions.assertEquals(ID, response.getBody().getId());
        Assertions.assertEquals(NAME, response.getBody().getName());
        Assertions.assertEquals(MAIL, response.getBody().getMail());
  //      Assertions.assertEquals(PASSWORD, response.getBody().getPassword());



    }


    @Test
    void whenDeleteThenReturnSuccess() {
        doNothing().when(service).delete(anyInt());

        ResponseEntity<UserDTO> response = resource.delete(ID);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(ResponseEntity.class, response.getClass());
        verify(service, times(1)).delete(anyInt());
        Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

    }

    private void startUser() {
        user = new User(ID, NAME, MAIL, PASSWORD);
        userDTO = new UserDTO(ID, NAME, MAIL, PASSWORD);

    }
}