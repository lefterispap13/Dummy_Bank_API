package com.dummyBank.controllers;

import com.dummyBank.models.Account;
import com.dummyBank.requests.AccountRequest;
import com.dummyBank.responses.AccountResponse;
import com.dummyBank.responses.Response;
import com.dummyBank.services.AccountServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static java.util.Objects.isNull;

@Slf4j
@CrossOrigin(origins = " * ", allowedHeaders = " * ")
@RequestMapping(value="/api/account")
@RestController
public class AccountController {

    @Autowired
    private AccountServiceImpl accountServiceImpl;


    // list of all the accounts not needed for bookstore
    @GetMapping(value="/getall")
    public AccountResponse getAll(){
        log.info("Ready to find all the Accounts");
        return new AccountResponse("Found all the Accounts",accountServiceImpl.getAll());
    }
//
    // get account by id not needed for bookstore
    @GetMapping(value="/getbyid/{id}")
    public AccountResponse getById(@PathVariable Long id){
        log.info("Ready to find account by id");
        return new AccountResponse("Found the account",accountServiceImpl.getAccountById(id));
    }

    //create new account not needed for bookstore
    @PostMapping(value="/new",consumes = "application/json",
            produces = "application/json")
    public AccountResponse createNewAccount(@RequestBody AccountRequest request){
        log.info("Ready to create a new Account");
        Long accountId=accountServiceImpl.createAccount(request);
        return new AccountResponse("The account has been saved",accountId);
    }

    // update account with id not needed for bookstore
    @PutMapping(value="/update/{id}",consumes = "application/json", produces = "application/json")
    public Response updateExistingAccount(@PathVariable(value = "id") Long id,
                                          @RequestBody AccountRequest request){
        log.info("ready to update an account");
        Account account = accountServiceImpl.updateAccount(id, request);
        if (isNull(account)) {
            return new Response("There is no such account");
        }
        return new Response("The account has been updated");
    }

    // delete account by id not needed for bookstore
    @DeleteMapping("/delete/{id}")
    public Response deleteAccount(@PathVariable Long id){
        log.info("Ready to delete an account");
        accountServiceImpl.deleteById(id);
        return new Response("The account has been deleted");
    }
}
