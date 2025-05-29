package org.example.rules;

public class RuleFactory {
    public static Rule fromString(String rule) {
        switch (rule.trim().toLowerCase()) {
            case "game of life":
            case "life":
                System.out.println(rule + "1");
                return new GameOfLifeRules();
            case "highlife":
                System.out.println(rule + "2");
                return new HighLifeRule();
            case "seeds":
                System.out.println(rule + "3");
                return new SeedsRule();
            case "day & night":
            case "day and night":
                System.out.println(rule + "4");
                return new DayAndNightRule();
            case "custom":
                System.out.println(rule + "5");
                return new CustomRule(); // Заглушка, можешь сделать редактор
            default:
                throw new IllegalArgumentException("Unknown rule: " + rule);
        }
    }
}
