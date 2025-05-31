package org.example.controller;

import org.example.db.CustomRuleRepository;
import org.example.model.GridModel;
import org.example.rules.Rule;
import org.example.rules.RuleFactory;
import org.example.view.ControlsView;

import javax.swing.*;
import java.util.List;

public class ControlsController {

    public ControlsController(GridModel model, ControlsView controlsView, CustomRuleRepository customRuleRepository) {
        populateCustomRules(controlsView.getRuleSelector(), customRuleRepository);

        controlsView.getRuleSelector().addActionListener(e -> {
            String selectedRule = (String) controlsView.getRuleSelector().getSelectedItem();
            if (selectedRule != null) {
                Rule rule = RuleFactory.getInstance().fromString(selectedRule);
                model.setRule(rule);
            }
        });
    }

    private void populateCustomRules(JComboBox<String> ruleSelector, CustomRuleRepository repository) {
        List<String> customNames = repository.findAllRuleNames();
        for (String name : customNames) {
            ruleSelector.addItem(name);
        }
    }
}
