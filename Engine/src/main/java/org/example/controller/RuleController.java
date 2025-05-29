package org.example.controller;

import org.example.model.GridModel;
import org.example.rules.Rule;
import org.example.rules.RuleFactory;
import org.example.view.ControlsView;
import org.example.view.RuleEditorView;

import javax.swing.*;

public class RuleController {
    public RuleController(GridModel model, RuleEditorView ruleEditorView, ControlsView controlsView) {
        // Кнопка "Add Rule"
        JButton addRuleButton = new JButton("➕ Add Rule");
        controlsView.getPanel().add(addRuleButton);  // Добавляем кнопку в интерфейс

        // Слушаем нажатие на "Add Rule"
        addRuleButton.addActionListener(e -> {
            ruleEditorView.showDialog(); // Показываем диалоговое окно
            String ruleName = ruleEditorView.getRuleName();
            String ruleText = ruleEditorView.getRuleText();

            if (ruleName != null && !ruleName.isEmpty() && ruleText != null && !ruleText.isEmpty()) {
                Rule rule = RuleFactory.fromString(ruleText);
                model.setRule(rule);

                // Добавляем в выпадающий список, если новое имя
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

                // Автоматически выбрать новое правило в списке
                selector.setSelectedItem(ruleName);
            }
        });
    }
}
