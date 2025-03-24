package com.educandoweb.course;

import com.educandoweb.course.entities.User;
import com.educandoweb.course.services.UserService;
import com.educandoweb.course.services.exceptions.DatabaseException;
import com.educandoweb.course.services.exceptions.ResourceNotFoundException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Mock
    private UserService service;


    //TESTE DE EXCESSAO - FINDBYID    
    @Test
    public void deveLancarResourceNotFoundException(){
        Assertions.assertThrows(ResourceNotFoundException.class, () -> userService.findById(1000000L));
    }

    @Test
    public void deveLancarInvalidDataAccessApiUsageException(){
        Assertions.assertThrows(InvalidDataAccessApiUsageException.class, () -> userService.findById(null));
    }

    //TESTE DE FUNCIONALIDADE - FINDBYID

    @ParameterizedTest
    @ValueSource(longs = {1L, 2L, 3L, 4L, 5L, 1})
    public void deveRetornarUmUsuarioDoBancoDeDados(Long id){
        Mockito.when(service.findById(id)).thenReturn(new User());

    }

    //TESTE DE EXCESSOES - INSERT

    @Test
    public void deveLancarIllegalArgumentExceptionQuandoObjetoNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> userService.insert(null));
    }

    //TESTE DE EXCESSOES - DELETE

    @Test
    public void deveLancarEmptyResultDataAccessExceptionQuandoIdInexistente() {
        Assertions.assertThrows(EmptyResultDataAccessException.class, () -> userService.delete(100000L));
    }
}
