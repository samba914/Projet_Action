package com.github.samba.mohamed.project_action.api;

import com.github.samba.mohamed.project_action.model.Account;
import com.github.samba.mohamed.project_action.payload.request.AddActionRequest;
import com.github.samba.mohamed.project_action.payload.request.BalanceRequest;
import org.jeasy.random.EasyRandom;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AccountApiTest {
    @Mock
    AccountApi accountApi ;
    EasyRandom random = new EasyRandom();

    @Test
    void getAccount() {
        Account account = random.nextObject(Account.class) ;
        String email  = random.nextObject(String.class) ;
        Mockito.when(accountApi.getAccount(email)).thenReturn(account) ;
        Assert.assertEquals(accountApi.getAccount(email), account);
    }

    @Test
    void addToAccountBalance() {
        Account account = random.nextObject(Account.class) ;
        String email  = random.nextObject(String.class) ;
        BalanceRequest balanceRequest = random.nextObject(BalanceRequest.class) ;
        Mockito.when(accountApi.addToAccountBalance(email,balanceRequest)).thenReturn(account) ;
        Assert.assertEquals(accountApi.addToAccountBalance(email,balanceRequest), account);
    }

    @Test
    void addAction() {
        Account account = random.nextObject(Account.class) ;
        AddActionRequest addActionRequest = random.nextObject(AddActionRequest.class) ;
        String email  = random.nextObject(String.class) ;
        Mockito.when(accountApi.addAction(email,addActionRequest)).thenReturn(account) ;
        Assert.assertEquals(accountApi.addAction(email,addActionRequest), account);
    }

    @Test
    void reduceAction() {
        Account account = random.nextObject(Account.class) ;
        AddActionRequest addActionRequest = random.nextObject(AddActionRequest.class) ;
        String email  = random.nextObject(String.class) ;
        Mockito.when(accountApi.reduceAction(email,addActionRequest)).thenReturn(account) ;
        Assert.assertEquals(accountApi.reduceAction(email,addActionRequest), account);
    }
}