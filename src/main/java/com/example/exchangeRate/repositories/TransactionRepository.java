package com.example.exchangeRate.repositories;

import com.example.exchangeRate.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long>
{
    List<Transaction> findByLimitExceededTrue();
}
