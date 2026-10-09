package com.example.demo.model;

import java.math.BigDecimal;

// Заявка на кредит, данные о клиенте, которые будут на входе (в заявке)
public record LoanApplication(
        int age,
        BigDecimal monthlyIncome,
        int workExperienceMonths,
        BigDecimal loanAmount,
        int termMonths,
        BigDecimal existingMonthlyPayments,
        int overdueCount
) {
}

