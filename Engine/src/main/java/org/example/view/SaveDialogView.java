package org.example.view;

import javax.swing.*;

public class SaveDialogView {
    private final JDialog dialog;
    private final JTextField nameField;
    private final JButton confirmButton;

    public SaveDialogView(JFrame parentFrame) {
        dialog = new JDialog(parentFrame, "Save Game", true);
        dialog.setSize(300, 150);
        dialog.setLocationRelativeTo(parentFrame);
        dialog.setLayout(new BoxLayout(dialog.getContentPane(), BoxLayout.Y_AXIS));

        dialog.add(new JLabel("Enter save name:"));
        nameField = new JTextField();
        dialog.add(nameField);

        confirmButton = new JButton("Save");
        dialog.add(confirmButton);
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
