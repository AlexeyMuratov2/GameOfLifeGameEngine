package org.example.db;

import java.time.LocalDateTime;

public class BoardSaveMeta {
    private final int id;
    private final String name;
    private final String ruleName;
    private final int rows;
    private final int cols;
    private final LocalDateTime createdAt;

    public BoardSaveMeta(int id, String name, String ruleName, int rows, int cols, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.ruleName = ruleName;
        this.rows = rows;
        this.cols = cols;
        this.createdAt = createdAt;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getRuleName() { return ruleName; }
    public int getRows() { return rows; }
    public int getCols() { return cols; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    @Override
    public String toString() {
        return name + " (" + createdAt + ")";
    }
}
