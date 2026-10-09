package com.example.demo.rule;

import com.example.demo.model.LoanApplication;
import com.example.demo.model.RuleResult;

public interface ScoringRule {
    RuleResult evaluate(LoanApplication application);
}
