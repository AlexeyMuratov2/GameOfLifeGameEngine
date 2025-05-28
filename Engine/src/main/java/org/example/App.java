package org.example;

import org.example.controller.GridClickController;
import org.example.model.*;
import org.example.rules.GameOfLifeRules;
import org.example.rules.Rule;
import org.example.view.*;


public class App {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            Rule rule = new GameOfLifeRules();
            GridModel model = new GridModel(30, 30, rule);

            GridView gridView = new GridView(30, 30);
            RuleEditorView ruleEditorView = new RuleEditorView();
            ControlsView controlsView = new ControlsView();

            new GridClickController(model, gridView);
//            new RuleController(model, ruleEditorView, controlsView);

            model.addObserver(gridView);

            new MainView(gridView, ruleEditorView, controlsView);
        });
    }
}
