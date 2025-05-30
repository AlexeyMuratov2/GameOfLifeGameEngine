package org.example.model;

import java.util.Stack;

public class GridHistoryManager {
    private final Stack<GridMemento> history = new Stack<>();

    public void save(GridMemento memento) {
        history.push(memento);
    }

    public boolean canUndo() {
        return !history.isEmpty();
    }

    public GridMemento undo() {
        if (canUndo()) {
            return history.pop();
        }
        return null;
    }

    public void clear() {
        history.clear();
    }
}
