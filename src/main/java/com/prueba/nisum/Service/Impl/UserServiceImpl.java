package com.prueba.nisum.Service.Impl;

import com.prueba.nisum.Model.User;
import com.prueba.nisum.Repository.UserRepository;
import com.prueba.nisum.Security.Jwt.JwtService;
import com.prueba.nisum.Service.UserService;
import com.prueba.nisum.Util.ResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final JwtService jwtService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, JwtService jwtService) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    @Override
    public ResponseObject createUser(User user) {
        try {
            // Validar que el correo electrónico tenga el formato correcto
            if (!isValidEmail(user.getEmail())) {
                return new ResponseObject("Error, El formato del correo electrónico es incorrecto");
            }

            // Validar que la contraseña tenga el formato correcto
            if (!isValidPassword(user.getPassword())) {
                return new ResponseObject("Error, El formato de la contraseña es incorrecto");
            }

            // Validar que el correo electrónico no esté registrado en la base de datos
            if (userRepository.existsUserByEmail(user.getEmail())) {
                return new ResponseObject("Error, El correo electrónico ya está registrado");
            }

            // Establecer fechas de creación y modificación
            Date currentDate = new Date();
            user.setCreated(currentDate);
            user.setModified(currentDate);
            user.setLastLogin(currentDate);
            // Generar el token JWT
            String token = jwtService.generateToken(user.getName());

            // Establecer el token en el usuario
            user.setToken(token);

            // Establecer isActive en true por defecto
            user.setActive(true);

            // Guardar el usuario en la base de datos
            userRepository.save(user);

            // Crear el objeto de respuesta con el mensaje de éxito y los datos del usuario creado
            return new ResponseObject("Usuario creado exitosamente", user);

        } catch (Exception ex) {
            // Manejar otras excepciones
            return new ResponseObject("Error interno del servidor"+ex);
        }
    }

    // Método para validar el formato del correo electrónico
    @Override
    public boolean isValidEmail(String email) {
        // Expresión regular para validar el formato del correo electrónico
        String emailRegex = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$";
        return email.matches(emailRegex);
    }

    // Método para validar el formato de la contraseña (debes implementar esta lógica)
    @Override
    public boolean isValidPassword(String password) {
        // Expresión regular para validar que la contraseña tenga al menos 8 caracteres, incluyendo al menos una letra mayúscula, una letra minúscula, un dígito y un carácter especial
        String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$";
        return password.matches(passwordRegex);
    }
}