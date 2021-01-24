package com.dummyBank.repository;

import com.dummyBank.models.CardDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardDetailsRepository extends JpaRepository<CardDetails,Long> {
    CardDetails findCardDetailsByCardNumber(Long cardNumber);
}
