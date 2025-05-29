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

    public ControlsView() {
        panel = new JPanel();

        startButton = new JButton("▶ Start");
        stopButton = new JButton("⏸ Stop");
        stepButton = new JButton("⏭ Step");
        ruleSelector = new JComboBox<>(new String[]{"Game of Life", "HighLife", "Day & Night", "Seeds"});

        saveButton = new JButton("💾 Save");

        panel.add(startButton);
        panel.add(stopButton);
        panel.add(stepButton);
        panel.add(ruleSelector);
        panel.add(saveButton);
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
}
