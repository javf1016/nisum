package com.prueba.nisum.Service;

import com.prueba.nisum.Model.User;
import com.prueba.nisum.Util.ResponseObject;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    public ResponseObject createUser(User user);
    public boolean isValidEmail(String email);
    public boolean isValidPassword(String password);

}
