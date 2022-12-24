package com.github.samba.mohamed.project_action.api;

import com.github.samba.mohamed.project_action.model.Account;
import com.github.samba.mohamed.project_action.payload.request.BalanceRequest;
import com.github.samba.mohamed.project_action.payload.response.MessageResponse;
import com.github.samba.mohamed.project_action.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8081",  allowCredentials="true")
@RestController
@AllArgsConstructor
@RequestMapping("/api/account")
public class AccountApi {
    private final AccountService accountService;

    @GetMapping("/getAccount/{email}")
    public Account getAccount(@PathVariable String email){

        return accountService.getAccountByEmail(email);
    }

    @PostMapping("/postAccount/{email}/{surname}/{name}")
    public Account postAccount(@PathVariable String email, @PathVariable String surname , @PathVariable String name){
        Account s = new Account(email,surname,name);
        return accountService.saveAccount(s);
    }

    @PutMapping("/addToAccountBalance/{email}")
    public Account addToAccountBalance(@PathVariable String email, @RequestBody BalanceRequest balance){
        float amount = balance.getAmount();
        return accountService.updateAccountBalance(email,amount);
    }



}
