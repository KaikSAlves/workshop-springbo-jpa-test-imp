package com.educandoweb.course;

import com.educandoweb.course.services.UserService;
import com.educandoweb.course.services.exceptions.ResourceNotFoundException;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.dao.InvalidDataAccessApiUsageException;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;


    //TESTE DE EXCESSAO - USER SERVICE    
    @Test
    public void deveLancarResourceNotFoundException(){
        assertThrows(ResourceNotFoundException.class, () -> userService.findById(10000000L));
    }

    @Test
    public void deveLancarInvalidDataAccessApiUsageException(){
        assertThrows(InvalidDataAccessApiUsageException.class, () -> userService.findById(null));
    }
}
