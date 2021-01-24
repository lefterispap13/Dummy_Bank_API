package com.dummyBank.responses;

import com.dummyBank.models.CardDetails;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CardDetailsResponse extends Response{

    private List<CardDetails> cardDetailsList;
    private CardDetails cardDetails;
    private Long cardId;
    private Long accountId;

    public CardDetailsResponse(String msg,List<CardDetails> cardDetailsList){
        super(msg);
        this.cardDetailsList=cardDetailsList;
    }

    public CardDetailsResponse(String msg,CardDetails cardDetails){
        super(msg);
        this.cardDetails=cardDetails;
    }

    public CardDetailsResponse(String msg,Long cardId){
        super(msg);
        this.cardId=cardId;
    }
}

