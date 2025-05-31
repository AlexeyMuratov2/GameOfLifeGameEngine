package org.example.rules;

import org.example.db.CustomRuleRepository;

public class RuleFactory {
    private static RuleFactory instance;

    private final CustomRuleRepository repository;

    private RuleFactory(CustomRuleRepository repository) {
        this.repository = repository;
    }

    public static void init(CustomRuleRepository repository) {
        if (instance == null) {
            instance = new RuleFactory(repository);
        }
    }

    public static RuleFactory getInstance() {
        if (instance == null) {
            throw new IllegalStateException("RuleFactory is not initialized. Call init() first.");
        }
        return instance;
    }


    public Rule fromString(String rule) {
        switch (rule.trim().toLowerCase()) {
            case "game of life":
            case "life":
                return new GameOfLifeRules();
            case "highlife":
                return new HighLifeRule();
            case "seeds":
                return new SeedsRule();
            case "day & night":
            case "day and night":
                return new DayAndNightRule();
            default:
                if (repository.ruleExists(rule.trim())) return new CustomRule(rule.trim(),repository.loadRuleByName(rule.trim()));
                throw new IllegalArgumentException("Unknown rule: " + rule);
        }
    }
}
