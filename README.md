# Nisum
Crear Usuario

Para abrir el H2 

* Driver Class:	org.h2.Driver
* JDBC URL:	jdbc:h2:mem:~/nisum
* User Name: sa
* Password:	

* http://localhost:8000/h2

Crear Usuario
El campo del password es validado con una expresión regular para que tenga al menos 8 caracteres, incluyendo al menos una letra mayúscula, una letra minúscula, un dígito y un carácter especial.

http://localhost:8000/api/users/create

{
  "name": "Juan Rodriguez",
  "email": "juanr@odriguez2.org",
  "password": "Password123!",
  "phones": [
    {
      "number": "1234567",
      "citycode": "1",
      "countrycode": "57"
    },
        {
      "number": "1234568",
      "citycode": "1",
      "countrycode": "57"
    }
  ]
}

# Salida Exitosa
* El token generado esta con expiracion de -1 por el ejercicio.
* Tambien se generaron los metodos en caso de generar la validacion del token al ingresar con el Bearer, ademas de la configuración de las paginas a las cuales se tiene acceso.

![Exitoso](https://github.com/javf1016/Images/blob/main/CrearUsuarioExitoso.PNG?raw=true)

# Email repetido
![Exitoso](https://github.com/javf1016/Images/blob/main/CrearUsuarioNoExitoso.PNG?raw=true)

# Swagger
* http://localhost:8000/swagger-ui.html
![Exitoso](https://github.com/javf1016/Images/blob/main/CrearUsuarioSwagger.PNG?raw=true)

# Diagrama de la solución
![Exitoso](https://github.com/javf1016/Images/blob/main/CrearUsuarioDiagrama.PNG?raw=true)
