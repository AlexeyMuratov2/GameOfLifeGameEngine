package org.example.rules;

public class DayAndNightRule implements Rule {
    @Override
    public boolean computeNextState(boolean currentStatus, int aliveNeighbours) {
        if (currentStatus) {
            return aliveNeighbours == 3 || aliveNeighbours == 4 ||
                    aliveNeighbours == 6 || aliveNeighbours == 7 || aliveNeighbours == 8;
        } else {
            return aliveNeighbours == 3 || aliveNeighbours == 6 ||
                    aliveNeighbours == 7 || aliveNeighbours == 8;
        }
    }
}
