package org.example.view;

import javax.swing.*;
import java.awt.*;

public class ControlsView implements View {
    private final JPanel panel;
    private final JButton startButton;
    private final JButton stopButton;
    private final JButton stepButton;
    private final JComboBox<String> ruleSelector;
    private final JButton saveButton;
    private final JButton returnButton;
    private final JButton undoButton;

    public ControlsView() {
        panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panel.setBackground(Color.WHITE);

        Font btnFont = new Font("SansSerif", Font.PLAIN, 14);

        startButton = new JButton("Start");
        stopButton = new JButton("Stop");
        stepButton = new JButton("Step");
        undoButton = new JButton("Undo");
        ruleSelector = new JComboBox<>(new String[]{"Game of Life", "HighLife", "Day & Night", "Seeds"});

        saveButton = new JButton("Save");
        returnButton = new JButton("Return");

        JButton[] buttons = {startButton, stopButton, stepButton, undoButton, saveButton, returnButton};
        for (JButton b : buttons) {
            b.setFont(btnFont);
            b.setFocusPainted(false);
            b.setBackground(new Color(230, 230, 230));
            b.setForeground(Color.DARK_GRAY);
            b.setBorder(BorderFactory.createLineBorder(new Color(160, 160, 160)));
        }

        ruleSelector.setFont(btnFont);
        ruleSelector.setBackground(Color.WHITE);
        ruleSelector.setForeground(Color.DARK_GRAY);
        ruleSelector.setBorder(BorderFactory.createLineBorder(new Color(160, 160, 160)));

        panel.add(startButton);
        panel.add(stopButton);
        panel.add(stepButton);
        panel.add(undoButton);
        panel.add(ruleSelector);
        panel.add(saveButton);
        panel.add(returnButton);
    }

    @Override
    public JPanel getPanel() {
        return panel;
    }

    public JButton getStartButton() { return startButton; }
    public JButton getStopButton() { return stopButton; }
    public JButton getStepButton() { return stepButton; }
    public JComboBox<String> getRuleSelector() { return ruleSelector; }
    public JButton getSaveButton() { return saveButton; }
    public JButton getReturnButton() { return returnButton; }
    public JButton getUndoButton() { return undoButton; }
}
