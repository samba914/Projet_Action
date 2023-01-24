package com.github.samba.mohamed.project_action.api;

import com.github.samba.mohamed.project_action.model.Account;
import com.github.samba.mohamed.project_action.model.Action;
import com.github.samba.mohamed.project_action.payload.request.AddActionRequest;
import com.github.samba.mohamed.project_action.payload.request.BalanceRequest;
import com.github.samba.mohamed.project_action.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "https://front-action-safe.azurewebsites.net/",  allowCredentials="true")
@RestController
@AllArgsConstructor
@RequestMapping("/api/account")
public class AccountApi {
    private final AccountService accountService;

    @GetMapping("/getAccount/{email}")
    public Account getAccount(@PathVariable String email){
        return accountService.getAccountByEmail(email);
    }

    @PutMapping("/addToAccountBalance/{email}")
    public Account addToAccountBalance(@PathVariable String email, @RequestBody BalanceRequest balance){
        float amount = balance.getAmount();
        return accountService.updateAccountBalance(email,amount);
    }
    @PutMapping("/addAction/{email}")
    public Account addAction(@PathVariable String email, @RequestBody AddActionRequest request){
        return accountService.addOrReduceAction(email,request,1);
    }

    @PutMapping("/reduceAction/{email}")
    public Account reduceAction(@PathVariable String email, @RequestBody AddActionRequest request){
        Account a =accountService.addOrReduceAction(email,request,-1);
        List<Action> l = a.getActifs().stream().filter(action->action.getQuantity()!=0).toList();
        a.setActifs(l);
        return a;
    }



}
