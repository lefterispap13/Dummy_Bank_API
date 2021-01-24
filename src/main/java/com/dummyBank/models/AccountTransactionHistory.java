package com.dummyBank.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "account_transaction_history")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountTransactionHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private Long transactionId;

    @Column(name = "transaction_timestamp",nullable = false)
    private LocalDateTime transactionDateTime;

    @Column(name = "transaction_type",nullable = false)
    private String transactionType;

    @Column(name = "currency",nullable = false)
    private double currency;

    @ManyToOne
    @JoinColumn(name="account_id",referencedColumnName="account_id",nullable = false)//,nullable=false)
//    @JsonIgnore
    private Account account;

    public AccountTransactionHistory(LocalDateTime transactionDateTime, String transactionType, double currency, Account account) {
        this.transactionDateTime = transactionDateTime;
        this.transactionType = transactionType;
        this.currency = currency;
        this.account = account;
    }
}
