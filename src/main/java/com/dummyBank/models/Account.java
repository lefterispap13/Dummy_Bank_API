package com.dummyBank.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "accounts")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Long accountId;

    @Column(name = "currency", nullable = false)
    private double totalCurrency;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "date_of_birth",nullable = false)
    private Date dateOfBirth;

    @ToString.Exclude
    @OneToOne(mappedBy = "account")
    @JsonIgnore
    private CardDetails cardDetails;

    @ToString.Exclude
    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<AccountTransactionHistory> accountTransactionHistory;

    public Account(double totalCurrency, String firstName, String lastName, Date dateOfBirth) {
        this.accountId = accountId;
        this.totalCurrency = totalCurrency;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }


}
