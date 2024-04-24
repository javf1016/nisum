package com.prueba.nisum.Util;

import com.prueba.nisum.Model.User;
import lombok.Data;

import java.util.Date;

@Data
public class ResponseObject {
    private String mensaje;
    private String id;
    private Date created;
    private Date modified;
    private Date lastLogin;
    private String token;
    private boolean isActive;

    public ResponseObject(String mensaje) {
        this.mensaje = mensaje;
    }

    public ResponseObject(String mensaje, User user) {
        this.mensaje = mensaje;
        if (user != null) {
            this.id = user.getId();
            this.created = user.getCreated();
            this.modified = user.getModified();
            this.lastLogin = user.getLastLogin();
            this.token = user.getToken();
            this.isActive = user.isActive();
        }
    }
}