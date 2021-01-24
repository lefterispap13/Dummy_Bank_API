package com.dummyBank.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "card_details")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "card_id")
    private Long cardId;

    @Column(name = "creation_date",nullable = false)
    private Date creationDate;

    @Column(name = "expiration_date",nullable = false)
    private Date expirationDate;

    @Column(name = "card_number",unique = true, nullable = false)
    private Long cardNumber;

    @Column(name = "name_on_card",nullable = false)
    private String nameOnCard;

    @Column(name = "type",nullable = false)
    private String typeOfCard;

    @Column(name = "cvv_code",nullable = false)
    private Long cvvCode;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="account_id",referencedColumnName = "account_id",unique = true,nullable = false)
    private Account account;

    public CardDetails(Date creationDate, Date expirationDate, Long cardNumber, String nameOnCard, String typeOfCard, Long cvvCode, Account account) {
        this.creationDate = creationDate;
        this.expirationDate = expirationDate;
        this.cardNumber = cardNumber;
        this.nameOnCard = nameOnCard;
        this.typeOfCard = typeOfCard;
        this.cvvCode = cvvCode;
        this.account = account;
    }

    public CardDetails(Date creationDate, Date expirationDate, Long cardNumber, String nameOnCard, String typeOfCard, Long cvvCode) {
        this.creationDate = creationDate;
        this.expirationDate = expirationDate;
        this.cardNumber = cardNumber;
        this.nameOnCard = nameOnCard;
        this.typeOfCard = typeOfCard;
        this.cvvCode = cvvCode;
    }

    public CardDetails(Date expirationDate, Long cardNumber, String nameOnCard, String typeOfCard, Long cvvCode) {
        this.expirationDate = expirationDate;
        this.cardNumber = cardNumber;
        this.nameOnCard = nameOnCard;
        this.typeOfCard = typeOfCard;
        this.cvvCode = cvvCode;
    }
}
