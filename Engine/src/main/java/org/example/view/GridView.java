package org.example.view;

import javax.swing.*;
import java.awt.*;

public class GridView implements View {
    private final JPanel panel;
    private final int rows;
    private final int cols;
    private final JPanel[][] cells;

    public GridView(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.panel = new JPanel(new GridLayout(rows, cols));
        this.cells = new JPanel[rows][cols];

        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                JPanel cell = new JPanel();
                cell.setBackground(Color.WHITE);
                cell.setBorder(BorderFactory.createLineBorder(Color.GRAY));
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
}
