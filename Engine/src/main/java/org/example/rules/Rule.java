package org.example.rules;

public interface Rule {
    boolean computeNextState(boolean currentStatus, int aliveNeighbours);
    String getName();
}
