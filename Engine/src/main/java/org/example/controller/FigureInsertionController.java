package org.example.controller;

import org.example.db.BoardSaveLoader;
import org.example.db.BoardSaveMeta;
import org.example.model.GridModel;
import org.example.view.SaveListPanel;
import org.example.view.GridView;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FigureInsertionController {
    private final GridModel model;
    private final GridView view;
    private final SaveListPanel saveListPanel;
    private final BoardSaveLoader saveLoader;

    public FigureInsertionController(GridModel model, GridView view, SaveListPanel saveListPanel, BoardSaveLoader saveLoader) {
        this.model = model;
        this.view = view;
        this.saveListPanel = saveListPanel;
        this.saveLoader = saveLoader;

        attachMouseListener();
    }

    private void attachMouseListener() {
        view.getPanel().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    int[] cell = view.getCellFromMousePosition(e.getX(), e.getY());
                    if (cell == null) return;

                    int startRow = cell[0];
                    int startCol = cell[1];

                    BoardSaveMeta selectedSave = saveListPanel.getSelectedSave();
                    if (selectedSave == null) return;

                    boolean[][] loadedFigure = saveLoader.loadGridFromDatabase(selectedSave.getId());

                    insertFigure(startRow, startCol, loadedFigure);
                }
            }
        });
    }

    private void insertFigure(int startRow, int startCol, boolean[][] figure) {
        int figRows = figure.length;
        int figCols = figure[0].length;

        for (int y = 0; y < figRows; y++) {
            for (int x = 0; x < figCols; x++) {
                if (figure[y][x]) {
                    int targetRow = startRow + y;
                    int targetCol = startCol + x;
                    if (model.isValidCell(targetRow, targetCol)) {
                        model.setCellAlive(targetRow, targetCol, true);
                    }
                }
            }
        }
        model.notifyObservers();
    }
}
