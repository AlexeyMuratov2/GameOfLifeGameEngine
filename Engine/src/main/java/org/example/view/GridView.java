package org.example.view;

import org.example.model.GridModelObserver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GridView implements View, GridModelObserver {
    private final JPanel panel;
    private int rows;
    private int cols;
    private JPanel[][] cells;

    // Интерфейс-обработчик кликов
    public interface CellClickListener {
        void onCellClicked(int row, int col);
    }

    private CellClickListener cellClickListener;

    public GridView(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.panel = new JPanel(new GridLayout(rows, cols));
        this.cells = new JPanel[rows][cols];

        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                final int row = y;
                final int col = x;

                JPanel cell = new JPanel();
                cell.setBackground(Color.WHITE);
                cell.setBorder(BorderFactory.createLineBorder(Color.GRAY));


                cells[y][x] = cell;
                panel.add(cell);
            }
        }
    }

    public void enableDrawingSupport() {
        panel.setFocusable(true); // На всякий случай
    }

    public void updateCell(int x, int y, boolean alive) {
        cells[y][x].setBackground(alive ? Color.BLACK : Color.WHITE);
        cells[y][x].repaint();
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
        panel.setLayout(new GridLayout(newRows, newCols));
        this.rows = newRows;
        this.cols = newCols;
        JPanel[][] newCells = new JPanel[newRows][newCols];

        for (int y = 0; y < newRows; y++) {
            for (int x = 0; x < newCols; x++) {
                final int row = y;
                final int col = x;

                JPanel cell = new JPanel();
                cell.setBackground(Color.WHITE);
                cell.setBorder(BorderFactory.createLineBorder(Color.GRAY));


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
