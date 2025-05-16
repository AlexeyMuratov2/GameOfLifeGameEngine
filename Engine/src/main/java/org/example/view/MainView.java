package org.example.view;

import javax.swing.*;
import java.awt.*;

public class MainView extends JFrame {
    private final GridView gridView;
    private final RuleEditorView ruleEditorView;
    private final ControlsView controlsView;

    public MainView(GridView gridView, RuleEditorView ruleEditorView, ControlsView controlsView) {
        super("Клеточный автомат");

        this.gridView = gridView;
        this.ruleEditorView = ruleEditorView;
        this.controlsView = controlsView;

        setLayout(new BorderLayout());
        add(gridView.getPanel(), BorderLayout.CENTER);
        add(ruleEditorView.getPanel(), BorderLayout.EAST);
        add(controlsView.getPanel(), BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
