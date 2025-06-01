package org.example.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class SaveDialogView {
    private final JDialog dialog;
    private final JTextField nameField;
    private final JButton confirmButton;

    public SaveDialogView(JFrame parentFrame) {
        dialog = new JDialog(parentFrame, "Save Game", true);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setSize(350, 160);
        dialog.setLocationRelativeTo(parentFrame);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(new EmptyBorder(15, 20, 15, 20));
        mainPanel.setBackground(Color.WHITE);

        JLabel label = new JLabel("Enter save name:");
        label.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        label.setForeground(new Color(30, 30, 60));

        nameField = new JTextField();
        nameField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        nameField.setForeground(new Color(30, 30, 60));
        nameField.setBackground(new Color(245, 245, 245));
        nameField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(150, 150, 170)),
                BorderFactory.createEmptyBorder(5, 8, 5, 8)));

        confirmButton = new JButton("Save");
        confirmButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        confirmButton.setBackground(new Color(60, 80, 140));
        confirmButton.setForeground(Color.WHITE);
        confirmButton.setFocusPainted(false);
        confirmButton.setBorder(BorderFactory.createEmptyBorder(8, 20, 8, 20));
        confirmButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        JPanel centerPanel = new JPanel(new BorderLayout(5, 5));
        centerPanel.setBackground(Color.WHITE);
        centerPanel.add(label, BorderLayout.NORTH);
        centerPanel.add(nameField, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.add(confirmButton);

        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        dialog.setContentPane(mainPanel);
    }

    public void show() {
        nameField.setText("");
        dialog.setVisible(true);
    }

    public void close() {
        dialog.setVisible(false);
    }

    public String getSaveName() {
        return nameField.getText();
    }

    public JButton getConfirmButton() {
        return confirmButton;
    }
}
