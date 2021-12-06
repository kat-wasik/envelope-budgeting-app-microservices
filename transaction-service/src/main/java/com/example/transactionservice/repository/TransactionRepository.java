package com.example.transactionservice.repository;

import com.example.transactionservice.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findAllByAccount(Long account);
    Long deleteByAccount(Long account);

    @Query(value = "SELECT SUM(amount) FROM transaction WHERE account = ?1", nativeQuery = true)
    Long calculateBalanceByAccount(Long account);
}
