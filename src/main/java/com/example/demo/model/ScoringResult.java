package com.example.demo.model;

import java.util.List;

// Итог по заявке
public record ScoringResult(
        int totalScore,
        Decision decision,
        List<RuleResult> ruleResults
) {
}
