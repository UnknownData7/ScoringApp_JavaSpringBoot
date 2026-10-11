package com.example.demo.rule;

import com.example.demo.model.LoanApplication;
import com.example.demo.model.RuleResult;
import org.springframework.stereotype.Component;

// Правило по просрочкам
@Component
public class OverdueRule implements ScoringRule {


    @Override
    public RuleResult evaluate(LoanApplication application) {
        int overdueCount = application.overdueCount();

        if (overdueCount == 0) {
            return new RuleResult("Просрочки", 30, "Просрочек: " + overdueCount + ", просрочки отсутствуют");
        } else if (overdueCount == 1) {
            return new RuleResult("Просрочки", 10, "Просрочек: " + overdueCount + ", одна просрочка");
        } else if (overdueCount == 2) {
            return new RuleResult("Просрочки", 0,"Просрочек: " + overdueCount + ", пограничное количество просрочек");
        } else {
            return new RuleResult("Просрочки", -20, "Просрочек: " + overdueCount + ", количество просрочек вышло за допустимый предел");
        }
    }
}
