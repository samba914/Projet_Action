package com.github.samba.mohamed.project_action.service;

import com.github.samba.mohamed.project_action.model.Account;
import com.github.samba.mohamed.project_action.model.Action;
import com.github.samba.mohamed.project_action.payload.request.AddActionRequest;
import com.github.samba.mohamed.project_action.repository.AccountRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.AbstractCollection;
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
    //reduceActionQuantity

    public Account addOrReduceAction(String email, AddActionRequest request,int typeOperation){ //TypeOperation ==-1 if sell , 1 if buy
        Optional<Account> st = accountRepo.findByEmail(email);
        if(st.isPresent()){
            Account a=st.get();
            boolean isAnExistAction =false;
            for(Action act : a.getActifs()){
                if(act.getSymbol().equals(request.getStockSymbol())){
                    isAnExistAction=true;
                    act.setQuantity(act.getQuantity()+ (typeOperation * request.getStockQuantity() ));
                    break;
                }
            }
            if(!isAnExistAction){
                Action newAction = new Action(request.getStockSymbol(), request.getStockName(),request.getStockQuantity());
                a.addAction(newAction);
            }
            a.setBalanceAmount(a.getBalanceAmount() - (typeOperation * request.getPurchasePrice() ));
            return accountRepo.save(a);

        }
        return null;
    }


}
