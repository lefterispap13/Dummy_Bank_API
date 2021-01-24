package com.dummyBank.requests;

import com.dummyBank.models.Account;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardDetailsRequest {

    private Date creationDate;
    private Date expirationDate;
    private Long cardNumber;
    private String nameOnCard;
    private String typeOfCard;
    private Long cvvCode;
    private Long accountId;
    private double currencySpent;
    private String typeOfTransaction;

    public CardDetailsRequest(Date creationDate, Date expirationDate, Long cardNumber, String nameOnCard, String typeOfCard, Long cvvCode) {
        this.creationDate = creationDate;
        this.expirationDate = expirationDate;
        this.cardNumber = cardNumber;
        this.nameOnCard = nameOnCard;
        this.typeOfCard = typeOfCard;
        this.cvvCode = cvvCode;
    }

    public CardDetailsRequest(Date expirationDate, Long cardNumber, String nameOnCard, String typeOfCard, Long cvvCode) {
        this.expirationDate = expirationDate;
        this.cardNumber = cardNumber;
        this.nameOnCard = nameOnCard;
        this.typeOfCard = typeOfCard;
        this.cvvCode = cvvCode;
    }

    public CardDetailsRequest(Date expirationDate, Long cardNumber, String nameOnCard, String typeOfCard, Long cvvCode, double currencySpent,String typeOfTransaction) {
        this.expirationDate = expirationDate;
        this.cardNumber = cardNumber;
        this.nameOnCard = nameOnCard;
        this.typeOfCard = typeOfCard;
        this.cvvCode = cvvCode;
        this.currencySpent = currencySpent;
        this.typeOfTransaction=typeOfTransaction;
    }
}
