package com.github.samba.mohamed.project_action.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="searchhistory")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "searchhistory_id_sequence")
    @Column(name = "id", nullable = false)
    private Long id;



}
