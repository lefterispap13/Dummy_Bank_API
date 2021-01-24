package com.dummyBank.services;

import com.dummyBank.models.Account;
import com.dummyBank.repository.AccountRepository;
import com.dummyBank.requests.AccountRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.isNull;

@Slf4j
@Service
public class AccountServiceImpl implements IAccountService{

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public List<Account> getAll() {
        log.info("Ready to get all the Accounts");
        return accountRepository.findAll();
    }

    @Override
    public Account getAccountById(Long id) {
        log.info("Ready to get an account from the given id");
        return accountRepository.findById(id).orElse(null);
    }

    @Override
    public Account getAccountByCardId(Long id) {

        return null;
    }

    @Override
    public Long createAccount(AccountRequest request) {
        log.info("Ready to insert a new Account . The request is {}",request);
        Account account = new Account(request.getTotalCurrency(),request.getFirstName(),request.getLastName(),request.getDateOfBirth());
        Account newAccount = accountRepository.save(account);
        log.info("The new account is {}", newAccount);
        log.info("The account has been inserted to the DB");
        return newAccount.getAccountId();
    }

    @Override
    public Account updateAccount(Long id, AccountRequest request) {
        log.info("Ready to update an existing account");
        Account existingAccount = accountRepository.findById(id).orElse(null);
        if (isNull(existingAccount)) {
            log.info("The account does not exists");
            return null;
        }
        existingAccount.setFirstName(request.getFirstName());
        existingAccount.setLastName(request.getLastName());
        existingAccount.setTotalCurrency(request.getTotalCurrency());
        existingAccount.setDateOfBirth(request.getDateOfBirth());
        Account updatedAccount = accountRepository.save(existingAccount);
        log.info("The updated account is {}", updatedAccount);
        log.info("The updated account has been inserted to the DB");
        return updatedAccount;
    }

    @Override
    public Account updateAccountCurrency(Long id,double newCurrency,String typeOfTransaction) {
        log.info("Ready to update an existing account");
        Account existingAccount = accountRepository.findById(id).orElse(null);
        if (isNull(existingAccount)) {
            log.info("The account does not exists");
            return null;
        }
        if(typeOfTransaction.equals("ebuy")){
            existingAccount.setTotalCurrency(existingAccount.getTotalCurrency()-newCurrency);
        }
        else if(typeOfTransaction.equals("deposit")){
            existingAccount.setTotalCurrency(existingAccount.getTotalCurrency()+newCurrency);
        }
        else if(typeOfTransaction.equals("retrieval")){
            existingAccount.setTotalCurrency(existingAccount.getTotalCurrency()-newCurrency);
        }
        Account updatedAccount = accountRepository.save(existingAccount);
        log.info("The updated account is {}", updatedAccount);
        log.info("The updated account has been inserted to the DB");
        return updatedAccount;
    }


    @Override
    public boolean deleteById(Long id) {
        log.info("Ready to delete an account");
        if (accountRepository.existsById(id)){
            accountRepository.deleteById(id);
            log.info("account deleted successfully");
            return true;
        }
        log.info("account has not deleted successfully");
        return false;
    }
}
