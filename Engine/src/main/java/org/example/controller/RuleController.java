package org.example.controller;

import org.example.db.CustomRuleRepository;
import org.example.model.GridModel;
import org.example.rules.Rule;
import org.example.rules.RuleFactory;
import org.example.view.ControlsView;
import org.example.view.RuleEditorView;

import javax.swing.*;

public class RuleController {
    public RuleController(GridModel model, RuleEditorView ruleEditorView, ControlsView controlsView, CustomRuleRepository customRuleRepository) {
        JButton addRuleButton = new JButton("➕ Add Rule");
        controlsView.getPanel().add(addRuleButton);

        addRuleButton.addActionListener(e -> {
            ruleEditorView.showDialog();
            String ruleName = ruleEditorView.getRuleName();
            String ruleText = ruleEditorView.getRuleText();

            if (ruleName != null && !ruleName.isEmpty() && ruleText != null && !ruleText.isEmpty()) {
                customRuleRepository.saveRule(ruleName, ruleText);
                Rule rule = RuleFactory.getInstance().fromString(ruleName);
                model.setRule(rule);

                JComboBox<String> selector = controlsView.getRuleSelector();
                boolean exists = false;
                for (int i = 0; i < selector.getItemCount(); i++) {
                    if (selector.getItemAt(i).equals(ruleName)) {
                        exists = true;
                        break;
                    }
                }
                if (!exists) {
                    selector.addItem(ruleName);
                }

                selector.setSelectedItem(ruleName);
            }
        });
    }
}
