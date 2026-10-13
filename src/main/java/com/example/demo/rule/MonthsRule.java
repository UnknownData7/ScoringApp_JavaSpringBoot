package com.example.demo.rule;

import com.example.demo.model.LoanApplication;
import com.example.demo.model.RuleResult;
import org.springframework.stereotype.Component;

@Component
public class MonthsRule implements ScoringRule {

    @Override
    public RuleResult evaluate(LoanApplication application) {
        int termMonths = application.termMonths();

        if (termMonths > 30) {
            return new RuleResult("Срок", 0, "Срок: " + termMonths + ", недопустимый");
        }
        return new RuleResult("Срок", 30,"Срок: " + termMonths + ", допустимый");
    }
}
