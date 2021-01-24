package com.dummyBank.repository;

import com.dummyBank.models.AccountTransactionHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountTransactionHistoryRepository extends JpaRepository<AccountTransactionHistory,Long> {

}
