package com.dummyBank.services;

import com.dummyBank.models.AccountTransactionHistory;
import com.dummyBank.requests.AccountTransactionHistoryRequest;

import java.util.List;

public interface IAccountTransactionHistory {

    //not needed for group project not needed
    List<AccountTransactionHistory> getAll();

    //not needed
    AccountTransactionHistory getAccountTransactionHistoryById(Long id);

    //This is needed
    Long createAccountTransactionHistory(AccountTransactionHistoryRequest request);

    //this too
    String createAccountTransactionHistory(Long accountId,String typeofTransaction,double currencySpent);

    //update AccountTransactionHistory not needed
    AccountTransactionHistory updateAccountTransactionHistory(Long id, AccountTransactionHistoryRequest request);

    boolean deleteById(Long id);
}
