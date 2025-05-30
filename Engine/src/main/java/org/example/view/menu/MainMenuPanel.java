package org.example.view.menu;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class MainMenuPanel extends JPanel {
    private final JLabel titleLabel = new JLabel("Game of Life - Main Menu", SwingConstants.CENTER);
    private final JPanel saveListPanel = new JPanel();
    private final JTextField rowsField = new JTextField("20", 5);
    private final JTextField colsField = new JTextField("20", 5);
    private final JComboBox<String> ruleBox = new JComboBox<>();
    private final JButton startButton = new JButton("Start New Game");
    private final JButton manageRulesButton = new JButton("Manage Rules");
    private final JButton exitButton = new JButton("Exit");

    public MainMenuPanel() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        titleLabel.setFont(new Font("Arial", Font.BOLD, 26));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 10));
        add(titleLabel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridLayout(1, 2, 10, 10));

        // Список сохранений
        saveListPanel.setLayout(new BoxLayout(saveListPanel, BoxLayout.Y_AXIS));
        saveListPanel.setBorder(BorderFactory.createTitledBorder("Saved Games"));
        centerPanel.add(new JScrollPane(saveListPanel));

        // Новая игра
        JPanel newGamePanel = new JPanel();
        newGamePanel.setLayout(new BoxLayout(newGamePanel, BoxLayout.Y_AXIS));
        newGamePanel.setBorder(BorderFactory.createTitledBorder("New Game"));

        JPanel sizePanel = new JPanel();
        sizePanel.add(new JLabel("Rows:"));
        sizePanel.add(rowsField);
        sizePanel.add(new JLabel("Cols:"));
        sizePanel.add(colsField);
        newGamePanel.add(sizePanel);

        JPanel rulePanel = new JPanel();
        rulePanel.add(new JLabel("Rule:"));
        rulePanel.add(ruleBox);
        newGamePanel.add(rulePanel);

        newGamePanel.add(startButton);
        newGamePanel.add(manageRulesButton);
        newGamePanel.add(exitButton);
        centerPanel.add(newGamePanel);

        add(centerPanel, BorderLayout.CENTER);
    }

    public JPanel getSaveListPanel() { return saveListPanel; }
    public JTextField getRowsField() { return rowsField; }
    public JTextField getColsField() { return colsField; }
    public JComboBox<String> getRuleBox() { return ruleBox; }
    public JButton getStartButton() { return startButton; }
    public JButton getManageRulesButton() { return manageRulesButton; }
    public JButton getExitButton() { return exitButton; }
}
