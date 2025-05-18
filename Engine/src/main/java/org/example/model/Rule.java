package org.example.model;

public interface Rule {
    boolean computeNextState(boolean currentStatus, int aliveNeighbours);
}
