package com.github.samba.mohamed.project_action.repository;

import com.github.samba.mohamed.project_action.model.SearchHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SearchHistotyRepository extends JpaRepository<SearchHistory,Long> {
    List<SearchHistory> findAll();
}
