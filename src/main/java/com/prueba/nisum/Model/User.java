package com.prueba.nisum.Model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    @ApiModelProperty(notes = "The database generated User ID")
    private String id;
    @Column(nullable = false)
    @ApiModelProperty(notes = "The name of the user")
    private String name;

    @ApiModelProperty(notes = "The email of the user")
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    @ApiModelProperty(notes = "The password of the user")
    private String password;
    @Column(nullable = false)
    @ApiModelProperty(notes = "The phones of the user")
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Phone> phones;
    @Column(nullable = false)
    @ApiModelProperty(notes = "The date created of the user")
    private Date created;
    @Column(nullable = false)
    @ApiModelProperty(notes = "The date modified of the user")
    private Date modified;
    @Column(nullable = false)
    @ApiModelProperty(notes = "The date last login of the user")
    private Date lastLogin;
    @Column(nullable = false)
    @ApiModelProperty(notes = "The token of the user")
    private String token;
    @Column(nullable = false)
    @ApiModelProperty(notes = "The user is active")
    private boolean isActive;

    // Método para establecer la relación entre el usuario y el teléfono antes de la persistencia
    @PrePersist
    public void prePersist() {
        if (phones != null) {
            for (Phone phone : phones) {
                phone.setUser(this);
            }
        }
    }
}
