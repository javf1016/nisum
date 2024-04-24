package com.prueba.nisum.Model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "phones")
@Data
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "The database generated phone ID")
    private Long id;
    @Column(nullable = false)
    @ApiModelProperty(notes = "The number of phone")
    private String number;
    @Column(nullable = false)
    @ApiModelProperty(notes = "The city code of phone")
    private String citycode;
    @Column(nullable = false)
    @ApiModelProperty(notes = "The country code of phone")
    private String countrycode;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @ApiModelProperty(notes = "The user owner this phone")
    private User user;
}
