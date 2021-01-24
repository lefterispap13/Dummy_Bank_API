package com.dummyBank.services;

import com.dummyBank.models.CardDetails;
import com.dummyBank.requests.CardDetailsRequest;

import java.util.List;

public interface ICardDetailsService {

    //not needed for group project not needed for group project
    List<CardDetails> getAll();

    //get CardDetails by id not needed for group project
    CardDetails getCardDetailsById(Long id);

    CardDetails getCardDetailsByCardNumber(Long id);
//
    //not needed for group project
    Long createCardDetails(CardDetailsRequest request);
//
    //update CardDetails not needed for group project
    CardDetails updateCardDetails(Long id, CardDetailsRequest request);

    //delete by id not needed for group project
    boolean deleteById(Long id);

    Long checkCardDetails(CardDetailsRequest request);
}
