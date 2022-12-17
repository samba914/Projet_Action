package com.github.samba.mohamed.project_action.service;

import com.github.samba.mohamed.project_action.model.Account;
import com.github.samba.mohamed.project_action.repository.AccountRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class AccountService {
    private final AccountRepository accountRepo;

    public Account saveAccount(Account account){
        return accountRepo.save(account);
    }
    public Account getAccountByEmail(String email){
        return accountRepo.findByEmail(email).orElse(null);
    }
}
