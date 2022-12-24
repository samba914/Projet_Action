package com.github.samba.mohamed.project_action.service;

import com.github.samba.mohamed.project_action.model.Account;
import com.github.samba.mohamed.project_action.model.Action;
import com.github.samba.mohamed.project_action.repository.AccountRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public Account updateAccountBalance(String email,float amount){
        Optional<Account> st = accountRepo.findByEmail(email);
        st.ifPresent(account-> account.setBalanceAmount(account.getBalanceAmount()+amount));
        return accountRepo.save(st.orElse(null));
    }


}
