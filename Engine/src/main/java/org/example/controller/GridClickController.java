package org.example.controller;

import org.example.model.GridModel;
import org.example.view.GridView;

public class GridClickController {

    private final GridModel model;
    private final GridView view;

    public GridClickController(GridModel model, GridView view) {
        this.model = model;
        this.view = view;

        attachListeners();
    }

    private void attachListeners() {
        view.setCellClickListener((row, col) -> {
            model.toggleCell(row, col);
        });
    }
}
