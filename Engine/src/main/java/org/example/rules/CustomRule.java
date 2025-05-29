package org.example.rules;

public class CustomRule implements Rule {
    @Override
    public boolean computeNextState(boolean currentStatus, int aliveNeighbours) {
        return false;
    }
}
