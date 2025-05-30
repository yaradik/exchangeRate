package com.example.exchangeRate.repositories;

import com.example.exchangeRate.entities.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LimitRepository extends JpaRepository<Limit, Long>
{
    Optional<Limit> findByExpenseCategory(String expenseCategory);
}
