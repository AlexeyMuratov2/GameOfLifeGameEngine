package org.example.controller;

import org.example.db.BoardSaveRepository;
import org.example.model.GridModel;
import org.example.view.ControlsView;
import org.example.view.SaveDialogView;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class SaveController {
    public SaveController(GridModel model, ControlsView controlsView, JFrame parentFrame, BoardSaveRepository repository) {
        SaveDialogView saveDialog = new SaveDialogView(parentFrame);

        controlsView.getSaveButton().addActionListener(e -> saveDialog.show());

        saveDialog.getConfirmButton().addActionListener(e -> {
            String name = saveDialog.getSaveName().trim();
            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(parentFrame, "Name cannot be empty.");
                return;
            }

            int rows = model.getRows();
            int cols = model.getCols();
            String ruleName = model.getRule().getName();

            List<int[]> liveCells = new ArrayList<>();
            for (int row = 0; row < rows; row++) {
                for (int col = 0; col < cols; col++) {
                    if (model.isCellAlive(row, col)) {
                        liveCells.add(new int[]{row, col});
                    }
                }
            }

            repository.saveBoard(name, ruleName, rows, cols, liveCells);
            saveDialog.close();
            JOptionPane.showMessageDialog(parentFrame, "Game saved!");
        });
    }
}
