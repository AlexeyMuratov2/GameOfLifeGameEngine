package org.example.rules;

public class RuleFactory {
    public static Rule fromString(String rule) {
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
                return new CustomRule(rule.trim().toLowerCase());
        }
    }
}
