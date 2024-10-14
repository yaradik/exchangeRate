package com.example.exchangeRate.repositories;

import com.example.exchangeRate.entities.CurrencyRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface CurrencyRateRepository extends JpaRepository<CurrencyRate, Long>
{
    Optional<CurrencyRate> findFirstByCurrencyPairAndRateDate(String currencyPair, LocalDate rateDate);

    Optional<CurrencyRate> findFirstByCurrencyPairOrderByRateDateDesc(String currencyPair);
}
