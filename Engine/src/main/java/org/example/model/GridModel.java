package org.example.model;

import org.example.rules.Rule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GridModel {
    private int rows;
    private int cols;
    private boolean[][] grid;
    private Rule rule;
    List<GridModelObserver> observers = new ArrayList<>();
    private final GridHistoryManager historyManager = new GridHistoryManager();

    public GridModel(int rows, int cols, Rule rule) {
        this.rows = rows;
        this.cols = cols;
        this.rule = rule;
        this.grid = new boolean[rows][cols];
    }

    public GridMemento createMemento() {
        return new GridMemento(this.grid);
    }

    public void restoreFromMemento(GridMemento memento) {
        boolean[][] restoredGrid = memento.getSavedState();
        int rows = restoredGrid.length;
        int cols = restoredGrid[0].length;

        // Обновляем текущую сетку
        this.grid = new boolean[rows][cols];
        for (int y = 0; y < rows; y++) {
            System.arraycopy(restoredGrid[y], 0, this.grid[y], 0, cols);
        }

        // Уведомить представление
        notifyObservers();
    }

    public GridHistoryManager getHistoryManager() {
        return historyManager;
    }

    public boolean getCell(int x, int y) {
        return grid[y][x];
    }

    public void setCell(int row, int col, boolean alive) {
        if (isValidCell(row, col)) {
            grid[row][col] = alive;
            notifyObservers();
        }
    }

    public void toggleCell(int row, int col) {
        grid[row][col] = !grid[row][col];
        notifyObservers(); // или вызвать обновление вручную
    }

    public boolean isValidCell(int row, int col) {
        return row >= 0 && row < grid.length && col >= 0 && col < grid[0].length;
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



    public boolean isCellAlive(int row, int col) {
        return isValidCell(row, col) && grid[row][col];
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

    public int getRows() {
        return rows;
    }

    public void reset(int newRows, int newCols, Rule newRule) {
        this.rows = newRows;
        this.cols = newCols;
        this.rule = newRule;
        this.grid = new boolean[rows][cols];
        notifyObservers();
    }

    public void resize(int newRows, int newCols) {
        this.rows = newRows;
        this.cols = newCols;
        this.grid = new boolean[rows][cols];
    }

    public void clear() {
        for (int row = 0; row < rows; row++) {
            Arrays.fill(grid[row], false);
        }
    }

    public void setCellAlive(int row, int col, boolean alive) {
        if (row >= 0 && row < rows && col >= 0 && col < cols) {
            grid[row][col] = alive;
        }
    }


    public int getCols() {
        return cols;
    }

    public Rule getRule() {
        return rule;
    }
}