package com.example.exchangeRate.services;

import com.example.exchangeRate.entities.Limit;
import com.example.exchangeRate.entities.Transaction;
import com.example.exchangeRate.repositories.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService
{
    private final TransactionRepository transactionRepository;
    private final CurrencyService currencyService;
    private final LimitService limitService;

    public Transaction saveTransaction(Transaction transaction) {
        // Получаем курс на день транзакции
        BigDecimal rate = currencyService.getRateForDate(transaction.getCurrencyShortname() + "/USD", transaction.getDatetime().toLocalDate());

        // Конвертируем сумму в USD
        BigDecimal expenseInUSD = transaction.getSum().multiply(rate);

        // Получаем текущий лимит для категории расхода
        Limit currentLimit = limitService.getCurrentLimit(transaction.getExpenseCategory());

        // Проверяем превышение лимита
        if (expenseInUSD.compareTo(currentLimit.getLimitSum()) > 0) {
            transaction.setLimitExceeded(true);
        } else {
            transaction.setLimitExceeded(false);
        }

        // Сохраняем транзакцию в базу данных
        return transactionRepository.save(transaction);
    }

    public List<Transaction> getLimitExceededTransactions() {
        return transactionRepository.findByLimitExceededTrue();
    }
}
