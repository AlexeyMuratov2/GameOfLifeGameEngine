package org.example.rules;

import java.util.HashSet;
import java.util.Set;

public class CustomRule implements Rule {
    private final Set<Integer> birthConditions = new HashSet<>();
    private final Set<Integer> survivalConditions = new HashSet<>();

    public CustomRule(String ruleText) {
        parseRule(ruleText);
    }

    private void parseRule(String ruleText) {
        ruleText = ruleText.toUpperCase().trim();
        if (!ruleText.matches("B\\d*/S\\d*")) {
            throw new IllegalArgumentException("Invalid rule format. Use format like B3/S23");
        }

        String[] parts = ruleText.split("/");

        for (char c : parts[0].substring(1).toCharArray()) {
            birthConditions.add(Character.getNumericValue(c));
        }

        for (char c : parts[1].substring(1).toCharArray()) {
            survivalConditions.add(Character.getNumericValue(c));
        }
    }

    @Override
    public boolean computeNextState(boolean currentStatus, int aliveNeighbours) {
        if (currentStatus) {
            return survivalConditions.contains(aliveNeighbours);
        } else {
            return birthConditions.contains(aliveNeighbours);
        }
    }
}
