package com.dummyBank.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountRequest {
    private double totalCurrency;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private Long cardDetailsId;
    private Set<Long> transactionHistoryIds;// probably not needed

    public AccountRequest(double totalCurrency,String firstName,String lastName,Date dateOfBirth){
        this.totalCurrency=totalCurrency;
        this.firstName=firstName;
        this.lastName=lastName;
        this.dateOfBirth=dateOfBirth;
    }

    public AccountRequest(double totalCurrency, String firstName, String lastName, Date dateOfBirth, Long cardDetailsId) {
        this.totalCurrency = totalCurrency;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.cardDetailsId = cardDetailsId;
    }
}
