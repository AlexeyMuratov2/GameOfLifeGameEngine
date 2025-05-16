package org.example.view;

import javax.swing.*;

public class RuleEditorView implements View{
    private final JPanel panel;
    private final JTextField ruleField;
    private final JButton applyButton;

    public RuleEditorView() {
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        ruleField = new JTextField("B3/S23");
        applyButton = new JButton("Set the rule");

        panel.setBorder(BorderFactory.createTitledBorder("Rules"));
        panel.add(new JLabel("Enter the rule (for example, B3/S23):"));
        panel.add(ruleField);
        panel.add(applyButton);
    }

    @Override
    public JPanel getPanel() {
        return panel;
    }

    public String getRuleText() {
        return ruleField.getText();
    }

    public JButton getApplyButton() {
        return applyButton;
    }

}
