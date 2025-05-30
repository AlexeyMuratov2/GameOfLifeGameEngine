package org.example.controller;

import org.example.model.GridModel;
import org.example.view.GridView;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GridClickController {

    private final GridModel model;
    private final GridView view;
    private boolean isMousePressed = false;
    private boolean drawValue = true; // true - оживить, false - стереть
    private int lastRow = -1;
    private int lastCol = -1;

    public GridClickController(GridModel model, GridView view) {
        this.model = model;
        this.view = view;

        view.enableDrawingSupport();
        attachPanelMouseListeners();
    }

    private void attachPanelMouseListeners() {
        view.getPanel().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                isMousePressed = true;
                determineDrawValueAndApply(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                isMousePressed = false;
                lastRow = -1;
                lastCol = -1;
            }
        });

        view.getPanel().addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (isMousePressed) {
                    applyDrawValue(e);
                }
            }
        });
    }

    // Определяем, что делать: оживлять или стирать
    private void determineDrawValueAndApply(MouseEvent e) {
        Point p = e.getPoint();
        int[] cell = view.getCellFromMousePosition(p.x, p.y);
        if (cell != null) {
            int row = cell[0];
            int col = cell[1];

            drawValue = !model.isCellAlive(row, col); // если клетка была мертва -> оживим
            model.setCell(row, col, drawValue);
            lastRow = row;
            lastCol = col;
        }
    }

    private void applyDrawValue(MouseEvent e) {
        Point p = e.getPoint();
        int[] cell = view.getCellFromMousePosition(p.x, p.y);
        if (cell != null) {
            int row = cell[0];
            int col = cell[1];
            if (row != lastRow || col != lastCol) {
                model.setCell(row, col, drawValue);
                lastRow = row;
                lastCol = col;
            }
        }
    }
}
