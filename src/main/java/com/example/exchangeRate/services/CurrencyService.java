package com.example.exchangeRate.services;

import com.example.exchangeRate.dto.CurrencyClient;
import com.example.exchangeRate.dto.CurrencyRateResponse;
import com.example.exchangeRate.entities.CurrencyRate;
import com.example.exchangeRate.repositories.CurrencyRateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CurrencyService
{
    private final CurrencyClient currencyClient;
    private final CurrencyRateRepository currencyRateRepository;

    @Value("995dfc514f42434786e7b030b4e39b45")
    private String apiKey;

    public BigDecimal getRateForDate(String currencyPair, LocalDate date) {
        // Проверка, есть ли курс в базе данных
        Optional<CurrencyRate> rateOpt = currencyRateRepository.findFirstByCurrencyPairAndRateDate(currencyPair, date);
        if (rateOpt.isPresent()) {
            return rateOpt.get().getRate();
        }

        // Если данных нет, берём последний доступный курс
        rateOpt = currencyRateRepository.findFirstByCurrencyPairOrderByRateDateDesc(currencyPair);
        if (rateOpt.isPresent()) {
            return rateOpt.get().getRate();
        }

        // Запрашиваем курс у внешнего API
        CurrencyRateResponse response = currencyClient.getCurrencyRate(currencyPair, "1day", apiKey);
        BigDecimal rate = new BigDecimal(response.getValues().get(0).getClose());

        // Сохраняем курс в базу данных
        CurrencyRate newRate = new CurrencyRate();
        newRate.setCurrencyPair(currencyPair);
        newRate.setRate(rate);
        newRate.setRateDate(LocalDate.parse(response.getValues().get(0).getDatetime()));
        currencyRateRepository.save(newRate);

        return rate;
    }
}
