package com.example.exchangeRate.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "limits")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Limit
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal limitSum;
    private LocalDateTime limitDatetime;
    private String limitCurrencyShortname;
    private String expenseCategory;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Limit limit = (Limit) o;
        return id != null && Objects.equals(id, limit.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
