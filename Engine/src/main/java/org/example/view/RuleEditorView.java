package org.example.view;

import javax.swing.*;
import java.awt.*;

public class RuleEditorView {
    private final JDialog dialog;
    private final JTextField nameField;
    private final JTextField ruleField;
    private final JButton saveButton;

    public RuleEditorView(JFrame parentFrame) {
        dialog = new JDialog(parentFrame, "Add New Rule", true);
        dialog.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
        dialog.setSize(300, 200);
        dialog.setLocationRelativeTo(parentFrame);

        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        nameField = new JTextField();
        ruleField = new JTextField("B3/S23");
        saveButton = new JButton("Save");

        content.add(new JLabel("Rule name:"));
        content.add(nameField);
        content.add(Box.createVerticalStrut(5));
        content.add(new JLabel("Rule (e.g., B3/S23):"));
        content.add(ruleField);
        content.add(Box.createVerticalStrut(10));
        content.add(saveButton);

        dialog.setContentPane(content);

        saveButton.addActionListener(e -> dialog.setVisible(false));
    }

    public void showDialog() {
        nameField.setText("");
        ruleField.setText("B3/S23");
        dialog.setVisible(true);
    }

    public String getRuleName() {
        return nameField.getText().trim();
    }

    public String getRuleText() {
        return ruleField.getText().trim();
    }

    public JButton getSaveButton() {
        return saveButton;
    }
}
