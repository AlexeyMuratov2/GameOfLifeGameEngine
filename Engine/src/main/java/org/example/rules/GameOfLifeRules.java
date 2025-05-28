package org.example.rules;

public class GameOfLifeRules implements Rule {
    @Override
    public boolean computeNextState(boolean currentStatus, int aliveNeighbours) {
        if (currentStatus && (aliveNeighbours < 2 || aliveNeighbours > 3)) return false;
        else return !currentStatus && aliveNeighbours == 3;
    }
}
