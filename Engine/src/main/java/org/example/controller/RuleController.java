package org.example.controller;

import org.example.model.*;
import org.example.rules.Rule;
import org.example.rules.RuleFactory;
import org.example.view.RuleEditorView;

public class RuleController {
    public RuleController(GridModel model, RuleEditorView ruleEditor) {
        ruleEditor.getApplyButton().addActionListener(e -> {
            String ruleText = ruleEditor.getRuleText();
            System.out.println(ruleText);
            Rule rule = RuleFactory.fromString(ruleText);
            model.setRule(rule);
        });
    }
}