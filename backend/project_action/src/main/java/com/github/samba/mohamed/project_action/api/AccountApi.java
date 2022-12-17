package com.github.samba.mohamed.project_action.api;

import com.github.samba.mohamed.project_action.model.Account;
import com.github.samba.mohamed.project_action.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:8081", maxAge = 3600, allowCredentials="true")
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

}
