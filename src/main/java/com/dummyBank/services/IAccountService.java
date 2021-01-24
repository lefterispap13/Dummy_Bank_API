package com.dummyBank.services;

import com.dummyBank.models.Account;
import com.dummyBank.requests.AccountRequest;

import java.util.List;

public interface IAccountService {

    //not needed for group project
    List<Account> getAll();

    Account getAccountById(Long id);

    Account getAccountByCardId(Long id);

    //not needed for group project
    Long createAccount(AccountRequest request);

    //update account
    Account updateAccount(Long id, AccountRequest request);

    //update account currency after a transaction takes place
    Account updateAccountCurrency(Long id,double newCurrency,String typeOfTransaction);

    boolean deleteById(Long id);
}
