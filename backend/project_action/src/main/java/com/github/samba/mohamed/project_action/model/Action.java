package com.github.samba.mohamed.project_action.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="action")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Action {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO , generator = "action_id_sequence")
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "logo")
    private String logo;

    @Column(name = "symbol")
    private String symbol;

    @Column(name = "name")
    private String name;

    @Column(name = "purchasePrice")
    private float purchasePrice;

    @Column(name = "quantity")
    private float quantity;






}
