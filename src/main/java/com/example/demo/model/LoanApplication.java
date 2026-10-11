package com.example.demo.model;

import java.math.BigDecimal;

// Заявка на кредит, данные о клиенте, которые будут на входе (в заявке)
public record LoanApplication(
        int age, // возраст
        BigDecimal monthlyIncome, // доход
        int workExperienceMonths, // стаж работы
        BigDecimal loanAmount, // сумма кредита
        int termMonths, // сроки погашения
        BigDecimal existingMonthlyPayments, // текущие ежемесячные платежи по кредитам
        int overdueCount // просроченная задолженность
) {
}

