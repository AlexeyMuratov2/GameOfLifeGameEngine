package org.example.rules;

public class HighLifeRule implements Rule {
    @Override
    public boolean computeNextState(boolean currentStatus, int aliveNeighbours) {
        if (currentStatus) {
            return aliveNeighbours == 2 || aliveNeighbours == 3;
        } else {
            return aliveNeighbours == 3 || aliveNeighbours == 6;
        }
    }
}
