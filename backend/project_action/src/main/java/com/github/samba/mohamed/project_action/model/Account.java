package com.github.samba.mohamed.project_action.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="account")
@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "action_id_sequence")
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "surname")
    private String surname;

    @Column(name = "name")
    private String name;

    @Column(name = "balanceAmount")
    private float balanceAmount;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "account_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Action> actifs;

    public Account(String email, String surname, String name){
        this.email = email;
        this.surname = surname;
        this.name= name;
        this.balanceAmount = 0;
        this.actifs = new ArrayList<>();
    }


}
