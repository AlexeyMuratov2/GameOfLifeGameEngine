package org.example;

import org.example.controller.ControlsController;
import org.example.controller.GridClickController;
import org.example.controller.RuleController;
import org.example.controller.SimulationController;
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

            new SimulationController(model, controlsView);
            new ControlsController(model, controlsView);
//            new GridController(model, gridView);
            new GridClickController(model, gridView);
            new RuleController(model, ruleEditorView);

            model.addObserver(gridView);

            new MainView(gridView, ruleEditorView, controlsView);
        });
    }
}
