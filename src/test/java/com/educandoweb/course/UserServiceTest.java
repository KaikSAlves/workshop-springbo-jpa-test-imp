package com.educandoweb.course;

import com.educandoweb.course.entities.User;
import com.educandoweb.course.services.UserService;
import com.educandoweb.course.services.exceptions.ResourceNotFoundException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class UserServiceTest {

    @Mock
    private UserService userService;


    //TESTE DE EXCESSAO - FINDBYID    
    @Test
    public void deveLancarResourceNotFoundException(){
        Mockito.when(userService.findById(1000000L)).thenThrow(ResourceNotFoundException.class);
    }

    @Test
    public void deveLancarInvalidDataAccessApiUsageException(){
        Mockito.when(userService.findById(null)).thenThrow(InvalidDataAccessApiUsageException.class);
    }

    //TESTE DE FUNCIONALIDADE - FINDBYID

    @ParameterizedTest
    @ValueSource(longs = {1L, 2L, 3L, 4L, 5L, 1})
    public void deveRetornarUmUsuarioDoBancoDeDados(Long id){
        Mockito.when(userService.findById(id)).thenReturn(new User(id, "Nome","teste@gmail.com" , "1109812309", "asdasd"));
    }

    //TESTE DE EXCESSOES - INSERT

    @Test
    public void deveLancarIllegalArgumentExceptionQuandoObjetoNull(){
        Mockito.when(userService.insert(null)).thenThrow(IllegalArgumentException.class);
    }


}
