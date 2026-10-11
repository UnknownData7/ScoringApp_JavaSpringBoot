package com.example.demo.rule;

import com.example.demo.model.LoanApplication;
import com.example.demo.model.RuleResult;
import org.springframework.stereotype.Component;

// Правило по стажу
@Component
public class ExperienceRule implements ScoringRule{


    @Override
    public RuleResult evaluate(LoanApplication application) {
        int workExperienceMonths = application.workExperienceMonths();
        if (workExperienceMonths >= 36) {
            return new RuleResult("Стаж", 30, "Стаж: " + workExperienceMonths + ", стабильный стаж");
        } else if (workExperienceMonths >= 12) {
            return new RuleResult("Стаж", 20, "Стаж: " + workExperienceMonths + ", достаточный стаж");
        } else if (workExperienceMonths >= 6) {
            return new RuleResult("Стаж", 10, "Стаж: " + workExperienceMonths + ", небольшой стаж");
        } else {
            return new RuleResult("Стаж", 0, "Стаж: " + workExperienceMonths + ", стаж слишком мал");
        }
    }
}
