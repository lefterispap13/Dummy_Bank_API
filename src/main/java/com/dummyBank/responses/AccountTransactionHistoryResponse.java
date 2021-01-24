package com.dummyBank.responses;

import com.dummyBank.models.AccountTransactionHistory;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountTransactionHistoryResponse extends Response{

    private List<AccountTransactionHistory> accountTransactionHistoryList;
    private AccountTransactionHistory accountTransactionHistory;
    private Long transactionId;

    public AccountTransactionHistoryResponse(String msg,List<AccountTransactionHistory> accountTransactionHistoryList){
        super(msg);
        this.accountTransactionHistoryList=accountTransactionHistoryList;
    }

    public AccountTransactionHistoryResponse(String msg,AccountTransactionHistory accountTransactionHistory){
        super(msg);
        this.accountTransactionHistory=accountTransactionHistory;
    }

    public AccountTransactionHistoryResponse(String msg,Long transactionId){
        super(msg);
        this.transactionId=transactionId;
    }
}
