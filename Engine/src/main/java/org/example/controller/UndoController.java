package org.example.controller;

import org.example.model.GridModel;
import org.example.view.ControlsView;

public class UndoController {
    public UndoController(GridModel model, ControlsView controlsView) {
        controlsView.getUndoButton().addActionListener(e -> {
            if (model.getHistoryManager().canUndo()) {
                model.restoreFromMemento(model.getHistoryManager().undo());
            }
        });
    }
}
