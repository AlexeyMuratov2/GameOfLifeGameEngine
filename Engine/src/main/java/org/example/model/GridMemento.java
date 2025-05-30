package org.example.model;

public class GridMemento {
    private final boolean[][] snapshot;

    public GridMemento(boolean[][] state) {
        // Глубокая копия массива
        int rows = state.length;
        int cols = state[0].length;
        this.snapshot = new boolean[rows][cols];
        for (int i = 0; i < rows; i++) {
            System.arraycopy(state[i], 0, this.snapshot[i], 0, cols);
        }
    }

    public boolean[][] getSavedState() {
        // Возвращаем копию для безопасности
        int rows = snapshot.length;
        int cols = snapshot[0].length;
        boolean[][] copy = new boolean[rows][cols];
        for (int i = 0; i < rows; i++) {
            System.arraycopy(snapshot[i], 0, copy[i], 0, cols);
        }
        return copy;
    }
}
