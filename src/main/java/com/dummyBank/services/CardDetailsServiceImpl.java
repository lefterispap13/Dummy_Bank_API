package com.dummyBank.services;

import com.dummyBank.models.Account;
import com.dummyBank.models.CardDetails;
import com.dummyBank.repository.AccountRepository;
import com.dummyBank.repository.CardDetailsRepository;
import com.dummyBank.requests.CardDetailsRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


import static java.util.Objects.isNull;

@Slf4j
@Service
public class CardDetailsServiceImpl implements ICardDetailsService{
    @Autowired
    CardDetailsRepository cardDetailsRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    AccountTransactionHistoryServiceImpl accountTransactionHistoryServiceImpl;
    @Override
    public List<CardDetails> getAll() {
        log.info("Ready to get all the cardDetails");
        return cardDetailsRepository.findAll();
    }

    @Override
    public CardDetails getCardDetailsById(Long id) {
        log.info("Ready to get a card details information from the given id");
        return cardDetailsRepository.findById(id).orElse(null);
    }

    @Override
    public CardDetails getCardDetailsByCardNumber(Long number) {
        log.info("Ready to get a card details information from the given cardnumber");
        return cardDetailsRepository.findCardDetailsByCardNumber(number);
    }

    @Override
    public Long createCardDetails(CardDetailsRequest request) {
        log.info("Ready to find th account");
        Long accountId=request.getAccountId();
        Account account=accountRepository.findById(accountId).orElse(null);
        log.info("Creating new cardDetails");
        CardDetails cardDetails=new CardDetails(request.getCreationDate(),request.getExpirationDate(),
                request.getCardNumber(),request.getNameOnCard(),request.getTypeOfCard(),request.getCvvCode(),account);
        CardDetails newCardDetails=cardDetailsRepository.save(cardDetails);
        log.info("The new card details is {}", newCardDetails);
        log.info("The card details have been inserted to the DB");
        return cardDetails.getCardId();
    }

    @Override
    public Long checkCardDetails(CardDetailsRequest request){
        log.info("Ready to check if the card exists");
        double currencySpent=request.getCurrencySpent();
        log.info("money spent "+currencySpent);
        //one method that finds the card details by card number if they exist
        CardDetails checkCard=cardDetailsRepository.findCardDetailsByCardNumber(request.getCardNumber());
        log.info(""+checkCard);
        log.info("time of check"+checkCard.getExpirationDate().getTime());
        log.info("request "+request.getExpirationDate().getTime());
        if (checkCard==null){
            log.info("1 card doesn't exist");
            return 0L;// card doesn't exist
        }
        //if it exists check the other details to check if they match with the db.
        else {
            if((long)checkCard.getCvvCode()!=(long)request.getCvvCode()){
//                log.info("2 card doesn't exist");
//                log.info("checkard "+checkCard.getCvvCode());
//                log.info(" request "+request.getCvvCode());
                return 0L;
            }
            if(!checkCard.getNameOnCard().equals(request.getNameOnCard())){
//                log.info("4 card doesn't exist");
//                log.info("checkard "+checkCard.getNameOnCard());
//                log.info(" request "+request.getNameOnCard());
                return 0L;
            }
            if(!checkCard.getTypeOfCard().equals(request.getTypeOfCard())){
                log.info("5 card doesn't exist");
                log.info("checkard "+checkCard.getTypeOfCard());
                log.info(" request "+request.getTypeOfCard());
                return 0L;
            }
            if(checkCard.getExpirationDate().getMonth()!=request.getExpirationDate().getMonth()){
                log.info("3 card doesn't exist");
                log.info("checkard "+checkCard.getExpirationDate());
                log.info(" request "+request.getExpirationDate());
                return 0L;
            }
            if(checkCard.getExpirationDate().getYear()!=request.getExpirationDate().getYear()){
                log.info("4 card doesn't exist");
                log.info("checkard "+checkCard.getExpirationDate());
                log.info(" request "+request.getExpirationDate());
                return 0L;
            }
//            int month=checkCard.getExpirationDate().getMonth();
//            int year=checkCard.getExpirationDate().getYear();
//            int month2=request.getExpirationDate().getMonth();
//            int year2=request.getExpirationDate().getYear();
//            log.info("month "+month+" year "+year+" day "+checkCard.getExpirationDate().getDay());
//            if (month==month2){
//                log.info("qq");
//            }
//            log.info("month2 "+month2+ " year2 "+year2 +" day "+request.getExpirationDate().getDay());
//            log.info("checkard "+checkCard.getExpirationDate());
//            log.info(" request "+request.getExpirationDate());
            Long accountId=checkCard.getAccount().getAccountId();
//            log.info("account "+accountId);
            String typeofTransaction=request.getTypeOfTransaction();
//            log.info("type of transaction "+typeofTransaction);
            //The details are correct so the transaction takes place
            String transactionMessage=accountTransactionHistoryServiceImpl.createAccountTransactionHistory(accountId,typeofTransaction,currencySpent);
            if (transactionMessage.equals("The transaction was invalid")){
                return 0L;
            }
            accountTransactionHistoryServiceImpl.createAccountTransactionHistory(6L,"deposit",currencySpent);
        }

        return 1L;
    }
    @Override
    public CardDetails updateCardDetails(Long id, CardDetailsRequest request) {
        log.info("Ready to update an existing card details information");
        CardDetails existingCardDetails = cardDetailsRepository.findById(id).orElse(null);
        if (isNull(existingCardDetails)) {
            log.info("The CardDetails does not exists");
            return null;
        }
        existingCardDetails.setCardNumber(request.getCardNumber());
        existingCardDetails.setCreationDate(request.getCreationDate());
        existingCardDetails.setCvvCode(request.getCvvCode());
        existingCardDetails.setExpirationDate(request.getExpirationDate());
        existingCardDetails.setNameOnCard(request.getNameOnCard());
        existingCardDetails.setTypeOfCard(request.getTypeOfCard());
        CardDetails updatedCardDetails = cardDetailsRepository.save(existingCardDetails);
        log.info("The updated CardDetails is {}", updatedCardDetails);
        log.info("The updated CardDetails has been inserted to the DB");
        return updatedCardDetails;

    }

    @Override
    public boolean deleteById(Long id) {
        log.info("Ready to delete a card details info");
        if (cardDetailsRepository.existsById(id)){
            cardDetailsRepository.deleteById(id);
            log.info("order details deleted successfully");
            return true;
        }
        log.info("order details have not deleted successfully");
        return false;
    }
}
