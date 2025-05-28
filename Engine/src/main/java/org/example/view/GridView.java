package org.example.view;

import org.example.model.GridModelObserver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GridView implements View, GridModelObserver {
    private final JPanel panel;
    private final int rows;
    private final int cols;
    private final JPanel[][] cells;

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

                cell.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        if (cellClickListener != null) {
                            cellClickListener.onCellClicked(row, col);
                        }
                    }
                });

                cells[y][x] = cell;
                panel.add(cell);
            }
        }
    }

    public void updateCell(int x, int y, boolean alive) {
        cells[y][x].setBackground(alive ? Color.BLACK : Color.WHITE);
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

    @Override
    public void onGridUpdate(boolean[][] grid) {
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                updateCell(x, y, grid[y][x]);
            }
        }
    }
}
