package com.dummyBank.services;

import com.dummyBank.models.Account;
import com.dummyBank.models.AccountTransactionHistory;
import com.dummyBank.repository.AccountRepository;
import com.dummyBank.repository.AccountTransactionHistoryRepository;
import com.dummyBank.requests.AccountTransactionHistoryRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

import static java.util.Objects.isNull;

@Slf4j
@Service
public class AccountTransactionHistoryServiceImpl implements IAccountTransactionHistory{

    @Autowired
    AccountTransactionHistoryRepository accountTransactionHistoryRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    AccountServiceImpl accountServiceImpl;

    @Override
    public List<AccountTransactionHistory> getAll() {
        log.info("Ready to find all the Transaction history elements");
        return accountTransactionHistoryRepository.findAll();
    }

    @Override
    public AccountTransactionHistory getAccountTransactionHistoryById(Long id) {
        log.info("Searching for Transaction history by the given id");
        return accountTransactionHistoryRepository.findById(id).orElse(null);
    }

    @Override
    public Long createAccountTransactionHistory(AccountTransactionHistoryRequest request) {
        log.info("Searching for account with the given id");
        Long accountId=request.getAccountId();
        LocalDateTime now= LocalDateTime.now();
        Account account=accountRepository.findById(accountId).orElse(null);
//        double currencyBeforeUpdate=account.getTotalCurrency();
        log.info("Ready to create a transaction");
        AccountTransactionHistory accountTransactionHistory=
                new AccountTransactionHistory(now,request.getTransactionType(),request.getCurrency(),account);
        if (accountTransactionHistory.getTransactionType().equals("ebuy")||accountTransactionHistory.getTransactionType().equals("retrieval")){
            if((account.getTotalCurrency()-accountTransactionHistory.getCurrency()<0.0)){
                log.info("The transaction is not valid. It won't be inserted in the db");
                return 0L;
            }
        }
        AccountTransactionHistory newAccountTransactionHistory=accountTransactionHistoryRepository.save(accountTransactionHistory);
        String typeOfTransaction=accountTransactionHistory.getTransactionType();
        double transactionCurrency=accountTransactionHistory.getCurrency();
        log.info("The new transaction is {}", newAccountTransactionHistory);
        log.info("The transaction has been inserted to the DB");
        log.info("Updating account currency");
        account=accountServiceImpl.updateAccountCurrency(accountId,transactionCurrency,typeOfTransaction);
        accountRepository.save(account);
        return accountTransactionHistory.getTransactionId();
    }
    @Override
    public String createAccountTransactionHistory(Long accountId,String typeofTransaction,double currencySpent) {
        log.info("Searching for account with the given id");
        LocalDateTime now= LocalDateTime.now();
        Account account=accountRepository.findById(accountId).orElse(null);
    //  double currencyBeforeUpdate=account.getTotalCurrency();
        log.info("Ready to create a transaction");
        AccountTransactionHistory accountTransactionHistory= new AccountTransactionHistory(now,typeofTransaction,currencySpent,account);
        if (accountTransactionHistory.getTransactionType().equals("ebuy")||accountTransactionHistory.getTransactionType().equals("retrieval")){
            if((account.getTotalCurrency()-accountTransactionHistory.getCurrency()<0.0)){
                log.info("The transaction is not valid. It won't be inserted in the db");
                return "The transaction was invalid";
            }
        }
        AccountTransactionHistory newAccountTransactionHistory=accountTransactionHistoryRepository.save(accountTransactionHistory);
        String typeOfTransaction=accountTransactionHistory.getTransactionType();
        double transactionCurrency=accountTransactionHistory.getCurrency();
        log.info("The new transaction is {}", newAccountTransactionHistory);
        log.info("The transaction has been inserted to the DB");
        log.info("Updating account currency");
        account=accountServiceImpl.updateAccountCurrency(accountId,transactionCurrency,typeOfTransaction);
        accountRepository.save(account);
        return "The transaction was successful";
        }

    @Override
    public AccountTransactionHistory updateAccountTransactionHistory(Long id, AccountTransactionHistoryRequest request) {
        log.info("Ready to update an existing transaction");
        AccountTransactionHistory existingAccountTransactionHistory = accountTransactionHistoryRepository.findById(id).orElse(null);
        if (isNull(existingAccountTransactionHistory)) {
            log.info("The transaction does not exists");
            return null;
        }
        existingAccountTransactionHistory.setCurrency(request.getCurrency());
        existingAccountTransactionHistory.setTransactionDateTime(LocalDateTime.now());
        existingAccountTransactionHistory.setTransactionType(request.getTransactionType());
        AccountTransactionHistory updatedAccountTransactionHistory = accountTransactionHistoryRepository.save(existingAccountTransactionHistory);
        log.info("The updated transaction is {}", updatedAccountTransactionHistory);
        log.info("The updated transaction has been inserted to the DB");
        return updatedAccountTransactionHistory;
    }

    @Override
    public boolean deleteById(Long id) {
        log.info("Ready to delete a Transaction history info");
        if (accountTransactionHistoryRepository.existsById(id)){
            accountTransactionHistoryRepository.deleteById(id);
            log.info("transaction information deleted successfully");
            return true;
        }
        log.info("transaction information have not deleted successfully");
        return false;
    }
}
