package org.example.model;

public class GameState {
    private static volatile GameState instance;
    private boolean isRunning = false;

    private GameState() {}

    public static GameState getInstance() {
        if (instance == null) {
            synchronized (GameState.class) {
                if (instance == null) {
                    instance = new GameState();
                }
                return instance;
            }
        }
        return instance;
    }

    public boolean isRunning() { return isRunning; }

    public void setRunning(boolean isRunning) { this.isRunning = isRunning; }
}
