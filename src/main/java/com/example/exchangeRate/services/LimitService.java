package com.example.exchangeRate.services;

import com.example.exchangeRate.entities.Limit;
import com.example.exchangeRate.repositories.LimitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LimitService
{
    private LimitRepository limitRepository;

    // Получаем текущий лимит для заданной категории расходов
    public Limit getCurrentLimit(String expenseCategory) {
        return limitRepository.findByExpenseCategory(expenseCategory)
                .orElseThrow(() -> new RuntimeException("Limit not found for category: " + expenseCategory));
    }

    // Создание нового лимита
    public Limit saveNewLimit(Limit limit) {
        // Проверка, что дата установления лимита не в будущем или прошлом
        if (limit.getLimitDatetime().isAfter(LocalDateTime.now())) {
            throw new IllegalArgumentException("Limit date cannot be in the future");
        }
        return limitRepository.save(limit);
    }

    // Получение всех лимитов
    public List<Limit> getAllLimits() {
        return limitRepository.findAll();
    }
}
