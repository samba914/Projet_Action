package com.github.samba.mohamed.project_action.repository;

import com.github.samba.mohamed.project_action.model.Action;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActionRepository extends JpaRepository<Action,Long> {
}
