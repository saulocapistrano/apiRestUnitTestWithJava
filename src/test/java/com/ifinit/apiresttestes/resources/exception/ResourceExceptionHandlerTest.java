package com.ifinit.apiresttestes.resources.exception;

import com.ifinit.apiresttestes.services.exceptions.DataIntegratyViolationException;
import com.ifinit.apiresttestes.services.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ResourceExceptionHandlerTest {

    public static final String THIS_EMAIL_IS_ALREADY_REGISTERED_IN_THE_SYSTEM = "This email is already registered in the system.";
    @InjectMocks
    private  ResourceExceptionHandler exceptionHandler;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void whenObjectNotFoundExceptionThenReturnAResponseEntity() {

        ResponseEntity<StandardError> response = exceptionHandler
                .objectNotFound(
                        new ObjectNotFoundException("Object not found"),
                        new MockHttpServletRequest());
        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        Assertions.assertEquals(ResponseEntity.class, response.getClass());
        Assertions.assertEquals(StandardError.class, response.getBody().getClass());
        Assertions.assertEquals("Object not found", response.getBody().getError());
        Assertions.assertEquals(404, response.getBody().getStatus());
        Assertions.assertNotEquals("/user/2", response.getBody().getPath());
        Assertions.assertNotEquals(LocalDateTime.now(), response.getBody().getTimestemp());

    }

    @Test
    void whenDataIntegrityViolationException() {
        ResponseEntity<StandardError> response = exceptionHandler
                .dataIntegrityViolationException(
                        new DataIntegratyViolationException(THIS_EMAIL_IS_ALREADY_REGISTERED_IN_THE_SYSTEM),
                        new MockHttpServletRequest());
        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        Assertions.assertEquals(ResponseEntity.class, response.getClass());
        Assertions.assertEquals(StandardError.class, response.getBody().getClass());
        Assertions.assertEquals(THIS_EMAIL_IS_ALREADY_REGISTERED_IN_THE_SYSTEM, response.getBody().getError());
        Assertions.assertEquals(400, response.getBody().getStatus());

    }
}