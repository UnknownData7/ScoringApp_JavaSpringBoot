package com.example.demo.rule;

import com.example.demo.model.LoanApplication;
import com.example.demo.model.RuleResult;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

// Правило ПДН (показатель долговой нагрузки)
// ПДН = (текущие платежи + платёж по новому кредиту) / ежемесячный доход
// платёж = S · r · (1 + r)^n / ((1 + r)^n − 1)
@Component
public class DebtBurdenRule implements ScoringRule {

    private static final BigDecimal ANNUAL_RATE = new BigDecimal("0.15");

    @Override
    public RuleResult evaluate(LoanApplication application) {
        // Защита: при нулевом доходе или сроке расчёт упал бы с ошибкой деления
        if (application.monthlyIncome().compareTo(BigDecimal.ZERO) <= 0
                || application.termMonths() <= 0) {
            return new RuleResult("ПДН", -30, "Доход или срок кредита указаны некорректно");
        }

        // Месячная ставка = годовая / 12
        BigDecimal monthlyRate = ANNUAL_RATE.divide(BigDecimal.valueOf(12), 10, RoundingMode.HALF_UP);

        // (1 + r)^n
        int termMonths = application.termMonths();
        BigDecimal factor = BigDecimal.ONE.add(monthlyRate).pow(termMonths);

        // Платёж по новому кредиту
        BigDecimal loanAmount = application.loanAmount();
        BigDecimal payment = loanAmount.multiply(monthlyRate).multiply(factor)
                .divide(factor.subtract(BigDecimal.ONE), 2, RoundingMode.HALF_UP);

        // Общая нагрузка и ПДН
        BigDecimal income = application.monthlyIncome();
        BigDecimal totalPayments = application.existingMonthlyPayments().add(payment);
        BigDecimal pdn = totalPayments.divide(income, 4, RoundingMode.HALF_UP);

        // ПДН в процентах для комментария
        BigDecimal percent = pdn.multiply(BigDecimal.valueOf(100)).setScale(1, RoundingMode.HALF_UP);

        // Баллы: проверяем от худшего к лучшему
        if (pdn.compareTo(new BigDecimal("0.80")) >= 0) {
            return new RuleResult("ПДН", -30, "ПДН " + percent + "%: слишком высокий");
        }
        if (pdn.compareTo(new BigDecimal("0.50")) >= 0) {
            return new RuleResult("ПДН", 0, "ПДН " + percent + "%: высокая нагрузка");
        }
        if (pdn.compareTo(new BigDecimal("0.30")) >= 0) {
            return new RuleResult("ПДН", 15, "ПДН " + percent + "%: умеренная нагрузка");
        }
        return new RuleResult("ПДН", 30, "ПДН " + percent + "%: низкая нагрузка");
    }
}