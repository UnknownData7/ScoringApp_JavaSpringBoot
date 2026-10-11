package com.example.demo;

import com.example.demo.model.Decision;
import com.example.demo.model.LoanApplication;
import com.example.demo.model.RuleResult;
import com.example.demo.model.ScoringResult;
import com.example.demo.rule.ScoringRule;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScoringService {

    private final List<ScoringRule> rules;
    private static final int APPROVE_THRESHOLD = 80;
    private static final int REVIEW_THRESHOLD = 40;

    public ScoringService(List<ScoringRule> rules) {
        this.rules = rules;
    }

    public ScoringResult score(LoanApplication application) {
        List<RuleResult> ruleResults = new ArrayList<>();
        int totalScore = 0;

        for (ScoringRule rule : rules) {
            RuleResult result = rule.evaluate(application);
            ruleResults.add(result);
            totalScore += result.points();
        }

        Decision decision;
        if (totalScore >= APPROVE_THRESHOLD) {
            decision = Decision.APPROVED;
        } else if (totalScore >= REVIEW_THRESHOLD) {
            decision = Decision.MANUAL_REVIEW;
        } else {
            decision = Decision.REJECTED;
        }

        return new ScoringResult(totalScore, decision, ruleResults);

    }
}
