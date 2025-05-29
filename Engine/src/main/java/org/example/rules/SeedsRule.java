package org.example.rules;

public class SeedsRule implements Rule {
    @Override
    public boolean computeNextState(boolean currentStatus, int aliveNeighbours) {
        return !currentStatus && aliveNeighbours == 2;
    }
}
