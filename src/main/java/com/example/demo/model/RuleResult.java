package com.example.demo.model;

// Итог стратегии
public record RuleResult(
        String ruleName, // правило = возраст
        int points, // сколько баллов дало правило
        String comment // пояснение отказа
) {
}
