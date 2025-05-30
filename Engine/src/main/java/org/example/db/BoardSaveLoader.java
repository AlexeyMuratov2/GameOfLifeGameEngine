package org.example.db;

import java.util.List;

public class BoardSaveLoader {
    private final BoardSaveRepository repository;

    public BoardSaveLoader(BoardSaveRepository repository) {
        this.repository = repository;
    }

    /**
     * Загружает сетку сохранения как boolean[][] по идентификатору сохранения.
     */
    public boolean[][] loadGridFromDatabase(int saveId) {
        BoardSaveMeta meta = repository.loadBoardMetaById(saveId);
        int rows = meta.getRows();
        int cols = meta.getCols();

        boolean[][] grid = new boolean[rows][cols];

        List<int[]> liveCells = repository.loadLiveCellsBySaveId(saveId);
        for (int[] cell : liveCells) {
            int row = cell[0];
            int col = cell[1];
            if (row >= 0 && row < rows && col >= 0 && col < cols) {
                grid[row][col] = true;
            }
        }

        return grid;
    }
}
