package com.github.samba.mohamed.project_action.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="search_history")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "search_history_id_sequence")
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "symbol")
    private String symbol ;
    @Column(name = "name")
    private String name ;
    @Column(name = "date_search")
    private String date ;

    public SearchHistory(String symbol, String name, String date) {
        this.symbol = symbol;
        this.name = name;
        this.date = date;
    }
}
