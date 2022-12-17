package com.github.samba.mohamed.project_action.repository;

import com.github.samba.mohamed.project_action.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {
    public Optional<Account> findByEmail(String email);

}
