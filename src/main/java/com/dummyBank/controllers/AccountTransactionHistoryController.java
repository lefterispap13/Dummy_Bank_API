package com.dummyBank.controllers;

import com.dummyBank.models.AccountTransactionHistory;
import com.dummyBank.requests.AccountTransactionHistoryRequest;
import com.dummyBank.responses.AccountTransactionHistoryResponse;
import com.dummyBank.responses.Response;
import com.dummyBank.services.AccountTransactionHistoryServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static java.util.Objects.isNull;

@Slf4j
@CrossOrigin(origins = " * ", allowedHeaders = " * ")
@RequestMapping(value="/api/transaction")
@RestController
public class AccountTransactionHistoryController {
    @Autowired
    AccountTransactionHistoryServiceImpl accountTransactionHistoryServiceImpl;

    // list of all the Transactions
        @GetMapping(value="/getall")
        public AccountTransactionHistoryResponse getAll(){
            log.info("Ready to find all the Transactions");
            return new AccountTransactionHistoryResponse("Found all the Transactions",accountTransactionHistoryServiceImpl.getAll());
        }

        // get Transaction by id
        @GetMapping(value="/getbyid/{id}")
        public AccountTransactionHistoryResponse getById(@PathVariable Long id){
            log.info("Ready to find Transaction by id");
            return new AccountTransactionHistoryResponse("Found the Transaction",accountTransactionHistoryServiceImpl.getAccountTransactionHistoryById(id));
        }

        //create new Transaction
        @PostMapping(value="/new",consumes = "application/json",
                produces = "application/json")
        public AccountTransactionHistoryResponse createNewAccountTransactionHistory(@RequestBody AccountTransactionHistoryRequest request){
            log.info("Ready to create a new Transaction History");
            Long transactionId=accountTransactionHistoryServiceImpl.createAccountTransactionHistory(request);
            if(transactionId==0L){
                return new AccountTransactionHistoryResponse("The Transaction is not valid. Please check up with your Bank",transactionId);
            }
            return new AccountTransactionHistoryResponse("The Transaction has been saved",transactionId);
        }

        // update Transaction with id
        @PutMapping(value="/update/{id}",consumes = "application/json", produces = "application/json")
        public Response updateExistingAccountTransactionHistory(@PathVariable(value = "id") Long id,
                                                                @RequestBody AccountTransactionHistoryRequest request){
            log.info("ready to update a Transaction");
            AccountTransactionHistory accountTransactionHistory = accountTransactionHistoryServiceImpl.updateAccountTransactionHistory(id, request);
            if (isNull(accountTransactionHistory)) {
                return new Response("There is no such Transaction");
            }
            return new Response("The Transaction have been updated");
        }

        // delete Transaction by id
        @DeleteMapping("/delete/{id}")
        public Response deleteAccountTransactionHistory(@PathVariable Long id){
            log.info("Ready to delete a Transaction's info");
            accountTransactionHistoryServiceImpl.deleteById(id);
            return new Response("The Transaction has been deleted");
        }
}
