package org.example;

import org.example.view.*;

import javax.swing.*;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GridView gridView = new GridView(30, 30);
            RuleEditorView ruleEditorView = new RuleEditorView();
            ControlsView controlsView = new ControlsView();
            new MainView(gridView, ruleEditorView, controlsView);
        });
    }
}
