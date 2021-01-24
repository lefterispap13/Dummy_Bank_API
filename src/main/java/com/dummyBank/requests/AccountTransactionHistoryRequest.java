package com.dummyBank.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.security.Timestamp;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountTransactionHistoryRequest {

    private LocalDateTime transactionDateTime;
    private String transactionType;// retrieval or withdrawal
    private double currency;
    private Long accountId;

    public AccountTransactionHistoryRequest(LocalDateTime transactionDateTime, String transactionType, double currency) {
        this.transactionDateTime = transactionDateTime;
        this.transactionType = transactionType;
        this.currency = currency;
    }
}
