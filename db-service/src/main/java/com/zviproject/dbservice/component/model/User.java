package com.zviproject.dbservice.component.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "user", schema = "microservices")
@Getter
@Setter
//update only needed
@DynamicUpdate(true)

//insert the all fields
@DynamicInsert(false)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "age")
    //Create sub request for get age
    //@Fetch(FetchMode.SUBSELECT)
    @BatchSize(size = 5)
    //One to many
    private byte age;

}
