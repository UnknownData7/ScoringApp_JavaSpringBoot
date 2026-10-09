package com.example.demo.rule;


import com.example.demo.model.LoanApplication;
import com.example.demo.model.RuleResult;
import org.springframework.stereotype.Component;

@Component
public class AgeRule implements ScoringRule {

    @Override
    public RuleResult evaluate(LoanApplication application) {
        int age = application.age();
        if(age >= 21 && age <= 60) {
            return new RuleResult("Возраст", 25,
                    "Возраст " + age + "лет: в пределах нормы");
        }

        if ((age >= 18 && age < 21) || (age > 60 && age <= 65)) {
            return new RuleResult("Возраст", 10,
                    "Возраст " + age + "лет: пограничная возрастная группа");
        }

        return new RuleResult("Возраст", 0,
                "Возраст " + age + "лет: вне допустимого диапазона");
    }
}
