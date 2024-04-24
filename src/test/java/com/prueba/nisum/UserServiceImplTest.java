package com.prueba.nisum;

import com.prueba.nisum.Model.User;
import com.prueba.nisum.Repository.UserRepository;
import com.prueba.nisum.Security.Jwt.JwtService;
import com.prueba.nisum.Service.Impl.UserServiceImpl;
import com.prueba.nisum.Util.ResponseObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private JwtService jwtService;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateUser_Success() {
        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("Password123!");

        when(userRepository.existsUserByEmail(anyString())).thenReturn(false);
        when(jwtService.generateToken(anyString())).thenReturn("mockedToken");
        when(userRepository.save(any(User.class))).thenReturn(user);

        ResponseObject responseObject = userService.createUser(user);

        assertEquals("Usuario creado exitosamente", responseObject.getMensaje());
    }

    @Test
    public void testCreateUser_InvalidEmail() {
        User user = new User();
        user.setEmail("invalid-email");
        user.setPassword("Password123!");

        ResponseObject responseObject = userService.createUser(user);

        assertEquals("Error, El formato del correo electrónico es incorrecto", responseObject.getMensaje());
    }

    @Test
    public void testCreateUser_InvalidPassword() {
        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("invalid-password");

        ResponseObject responseObject = userService.createUser(user);

        assertEquals("Error, El formato de la contraseña es incorrecto", responseObject.getMensaje());
    }

    @Test
    public void testCreateUser_EmailAlreadyExists() {
        User user = new User();
        user.setEmail("existing@example.com");
        user.setPassword("Password123!");

        when(userRepository.existsUserByEmail(anyString())).thenReturn(true);

        ResponseObject responseObject = userService.createUser(user);

        assertEquals("Error, El correo electrónico ya está registrado", responseObject.getMensaje());
    }

}
