package org.example.controller;

import org.example.model.GridModel;
import org.example.rules.Rule;
import org.example.rules.RuleFactory;
import org.example.view.ControlsView;

public class ControlsController {
    public ControlsController(GridModel model, ControlsView controlsView) {
        controlsView.getRuleSelector().addActionListener(e -> {
            String selectedRule = (String) controlsView.getRuleSelector().getSelectedItem();
            if (selectedRule != null) {
                Rule rule = RuleFactory.fromString(selectedRule);
                model.setRule(rule);
            }
        });
//
//        controlsView.getStartButton().addActionListener(e -> model.startSimulation());
//        controlsView.getStopButton().addActionListener(e -> model.stopSimulation());
//        controlsView.getStepButton().addActionListener(e -> model.stepSimulation());
    }
}
