package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class GridModel {
    private final int rows;
    private final int cols;
    private boolean[][] grid;
    private Rule rule;
    List<GridModelObserver> observers = new ArrayList<>();

    public GridModel(int rows, int cols, Rule rule) {
        this.rows = rows;
        this.cols = cols;
        this.rule = rule;
        this.grid = new boolean[rows][cols];
    }

    public boolean getCell(int x, int y) {
        return grid[y][x];
    }

    public void setCell(int x, int y, boolean alive) {
        grid[y][x] = alive;
        notifyObservers();
    }

    public void setRule(Rule rule) {
        this.rule = rule;
    }

    public void addObserver(GridModelObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(GridModelObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        boolean[][] snapshot = getGridSnapshot();
        for (GridModelObserver observer : observers) {
            observer.onGridUpdate(snapshot);
        }
    }

    public void nextGeneration() {
        boolean[][] next = new boolean[rows][cols];
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                int aliveNeighbors = countAliveNeighbors(x, y);
                next[y][x] = rule.computeNextState(grid[y][x], aliveNeighbors);
            }
        }
        grid = next;
        notifyObservers();
    }

    private int countAliveNeighbors(int x, int y) {
        int count = 0;
        for (int dy = -1; dy <= 1; dy++) {
            for (int dx = -1; dx <= 1; dx++) {
                if (dx == 0 && dy == 0) continue;
                int nx = x + dx, ny = y + dy;
                if (nx >= 0 && ny >= 0 && nx < cols && ny < rows && grid[ny][nx]) {
                    count++;
                }
            }
        }
        return count;
    }

    public boolean[][] getGridSnapshot() {
        boolean[][] snapshot = new boolean[rows][cols];
        for (int y = 0; y < rows; y++) {
            System.arraycopy(grid[y], 0, snapshot[y], 0, cols);
        }
        return snapshot;
    }
}