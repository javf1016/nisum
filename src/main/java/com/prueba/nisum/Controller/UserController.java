package com.prueba.nisum.Controller;

import com.prueba.nisum.Model.User;
import com.prueba.nisum.Service.Impl.UserServiceImpl;
import com.prueba.nisum.Service.UserService;
import com.prueba.nisum.Util.ResponseObject;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@Api(value = "Appointment Management System", description = "Operations related to create users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseObject> createUser(@RequestBody User user) {
        // Invocar al servicio para crear el usuario
        ResponseObject responseObject = userService.createUser(user);

        // Verificar si la operación fue exitosa o no
        if (responseObject.getMensaje().startsWith("Error")) {
            // Sí hubo un error, devolver un ResponseEntity con el mensaje de error y el código de estado 400 (Bad Request)
            return ResponseEntity.badRequest().body(responseObject);
        } else {
            // Si la operación fue exitosa, devolver un ResponseEntity con el mensaje de éxito y el código de estado 200 (OK)
            return ResponseEntity.ok().body(responseObject);
        }
    }
}