package org.example.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class RuleEditorView {
    private final JDialog dialog;
    private final JTextField nameField;
    private final JTextField ruleField;
    private final JButton saveButton;

    public RuleEditorView(JFrame parentFrame) {
        dialog = new JDialog(parentFrame, "Add New Rule", true);
        dialog.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
        dialog.setSize(350, 220);
        dialog.setLocationRelativeTo(parentFrame);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(new EmptyBorder(15, 20, 15, 20));
        mainPanel.setBackground(Color.WHITE);

        JLabel nameLabel = new JLabel("Rule name:");
        nameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        nameLabel.setForeground(new Color(30, 30, 60));

        nameField = new JTextField();
        nameField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        nameField.setForeground(new Color(30, 30, 60));
        nameField.setBackground(new Color(245, 245, 245));
        nameField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(150, 150, 170)),
                BorderFactory.createEmptyBorder(5, 8, 5, 8)));

        JLabel ruleLabel = new JLabel("Rule (e.g., B3/S23):");
        ruleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        ruleLabel.setForeground(new Color(30, 30, 60));

        ruleField = new JTextField("B3/S23");
        ruleField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        ruleField.setForeground(new Color(30, 30, 60));
        ruleField.setBackground(new Color(245, 245, 245));
        ruleField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(150, 150, 170)),
                BorderFactory.createEmptyBorder(5, 8, 5, 8)));

        saveButton = new JButton("Save");
        saveButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        saveButton.setBackground(new Color(60, 80, 140));
        saveButton.setForeground(Color.WHITE);
        saveButton.setFocusPainted(false);
        saveButton.setBorder(BorderFactory.createEmptyBorder(8, 20, 8, 20));
        saveButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        mainPanel.add(nameLabel);
        mainPanel.add(Box.createVerticalStrut(5));
        mainPanel.add(nameField);
        mainPanel.add(Box.createVerticalStrut(15));
        mainPanel.add(ruleLabel);
        mainPanel.add(Box.createVerticalStrut(5));
        mainPanel.add(ruleField);
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(saveButton);

        dialog.setContentPane(mainPanel);

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
