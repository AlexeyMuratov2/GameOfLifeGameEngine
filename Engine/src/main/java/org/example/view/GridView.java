package org.example.view;

import org.example.model.GridModelObserver;
import org.example.view.View;

import javax.swing.*;
import java.awt.*;

public class GridView implements View, GridModelObserver {
    private final JPanel panel;
    private int rows;
    private int cols;
    private JPanel[][] cells;

    public interface CellClickListener {
        void onCellClicked(int row, int col);
    }

    private CellClickListener cellClickListener;

    public GridView(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.panel = new JPanel(new GridLayout(rows, cols, 1, 1)); // небольшой отступ между клетками
        this.panel.setBackground(new Color(220, 220, 220)); // фон панели — светло-серый
        this.cells = new JPanel[rows][cols];

        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                JPanel cell = new JPanel();
                cell.setBackground(Color.WHITE);
                cell.setBorder(BorderFactory.createLineBorder(new Color(180, 180, 180))); // светло-серая граница
                this.cells[y][x] = cell;
                panel.add(cell);
            }
        }
    }

    public void enableDrawingSupport() {
        panel.setFocusable(true);
    }

    public void updateCell(int x, int y, boolean alive) {
        Color aliveColor = new Color(30, 30, 30); // темно-серый вместо чёрного — более мягко
        cells[y][x].setBackground(alive ? aliveColor : Color.WHITE);
        cells[y][x].repaint();
    }

    public void updateSize(int rows, int cols) {
        rebuild(rows, cols);
    }

    @Override
    public JPanel getPanel() {
        return panel;
    }

    public JPanel getCellPanel(int row, int col) {
        return cells[row][col];
    }

    public void setCellClickListener(CellClickListener listener) {
        this.cellClickListener = listener;
    }

    public void rebuild(int newRows, int newCols) {
        panel.removeAll();
        panel.setLayout(new GridLayout(newRows, newCols, 1, 1));
        this.rows = newRows;
        this.cols = newCols;
        JPanel[][] newCells = new JPanel[newRows][newCols];

        for (int y = 0; y < newRows; y++) {
            for (int x = 0; x < newCols; x++) {
                JPanel cell = new JPanel();
                cell.setBackground(Color.WHITE);
                cell.setBorder(BorderFactory.createLineBorder(new Color(180, 180, 180)));
                newCells[y][x] = cell;
                panel.add(cell);
            }
        }
        this.cells = newCells;
        panel.revalidate();
        panel.repaint();
    }

    public int[] getCellFromMousePosition(int x, int y) {
        int panelWidth = panel.getWidth();
        int panelHeight = panel.getHeight();

        if (rows == 0 || cols == 0) return null;

        int cellWidth = panelWidth / cols;
        int cellHeight = panelHeight / rows;

        int col = x / cellWidth;
        int row = y / cellHeight;

        if (row >= 0 && row < rows && col >= 0 && col < cols) {
            return new int[]{row, col};
        }
        return null;
    }

    @Override
    public void onGridUpdate(boolean[][] grid) {
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                updateCell(x, y, grid[y][x]);
            }
        }
    }

    public int getRowCount() {
        return rows;
    }

    public int getColCount() {
        return cols;
    }
}
