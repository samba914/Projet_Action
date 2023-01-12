package com.github.samba.mohamed.project_action.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="user",uniqueConstraints = {
        @UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "email")
})
@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO , generator = "user_id_sequence")
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "surname")
    private String surname;

    @Column(name = "name")
    private String name;

    public User(String username, String email, String password,String surname, String name) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.surname = surname;
        this.name = name;
    }




}
