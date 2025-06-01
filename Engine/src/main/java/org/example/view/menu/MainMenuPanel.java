package org.example.view.menu;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class MainMenuPanel extends JPanel {
    private final JLabel titleLabel = new JLabel("Game of Life - Main Menu", SwingConstants.CENTER);
    private final JPanel saveListPanel = new JPanel();
    private final JTextField rowsField = new JTextField("20", 5);
    private final JTextField colsField = new JTextField("20", 5);
    private final JComboBox<String> ruleBox = new JComboBox<>();
    private final JButton startButton = new JButton("Start New Game");
    private final JButton manageRulesButton = new JButton("Manage Rules");
    private final JButton exitButton = new JButton("Exit");
    private final JButton manageSavesButton = new JButton("Manage Saves");

    // Цвета и шрифты для единого стиля
    private final Color backgroundColor = new Color(245, 247, 250);
    private final Color panelBackground = Color.WHITE;
    private final Color borderColor = new Color(180, 180, 180);
    private final Color buttonBg = new Color(60, 85, 130);
    private final Color buttonFg = Color.WHITE;
    private final Font titleFont = new Font("Georgia", Font.BOLD, 28);
    private final Font labelFont = new Font("Serif", Font.PLAIN, 16);
    private final Font inputFont = new Font("Serif", Font.PLAIN, 14);
    private final Font buttonFont = new Font("Serif", Font.BOLD, 14);

    public MainMenuPanel() {
        setBackground(backgroundColor);
        setLayout(new BorderLayout(15, 15));
        setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        titleLabel.setFont(titleFont);
        titleLabel.setForeground(new Color(30, 45, 75));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 30, 10));
        add(titleLabel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridLayout(1, 2, 25, 10));
        centerPanel.setBackground(backgroundColor);

        // Левая панель — сохранения
        JPanel saveListWrapperPanel = new JPanel(new BorderLayout(5, 5));
        saveListWrapperPanel.setBackground(panelBackground);
        saveListWrapperPanel.setBorder(new CompoundBorder(
                new TitledBorder(new LineBorder(borderColor, 1), "Saved Games", TitledBorder.LEADING, TitledBorder.TOP, labelFont, new Color(30, 45, 75)),
                new EmptyBorder(10, 10, 10, 10)
        ));

        saveListPanel.setLayout(new BoxLayout(saveListPanel, BoxLayout.Y_AXIS));
        saveListPanel.setBackground(panelBackground);
        JScrollPane scrollPane = new JScrollPane(saveListPanel);
        scrollPane.setBorder(BorderFactory.createLineBorder(borderColor, 1));
        saveListWrapperPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        buttonPanel.setBackground(panelBackground);
        styleButton(manageSavesButton);
        buttonPanel.add(manageSavesButton);
        saveListWrapperPanel.add(buttonPanel, BorderLayout.SOUTH);

        centerPanel.add(saveListWrapperPanel);

        // Правая панель — новая игра
        JPanel newGamePanel = new JPanel();
        newGamePanel.setLayout(new BoxLayout(newGamePanel, BoxLayout.Y_AXIS));
        newGamePanel.setBackground(panelBackground);
        newGamePanel.setBorder(new CompoundBorder(
                new TitledBorder(new LineBorder(borderColor, 1), "New Game", TitledBorder.LEADING, TitledBorder.TOP, labelFont, new Color(30, 45, 75)),
                new EmptyBorder(15, 15, 15, 15)
        ));

        JPanel sizePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 10));
        sizePanel.setBackground(panelBackground);
        JLabel rowsLabel = new JLabel("Rows:");
        JLabel colsLabel = new JLabel("Cols:");
        rowsLabel.setFont(labelFont);
        colsLabel.setFont(labelFont);
        rowsField.setFont(inputFont);
        colsField.setFont(inputFont);
        rowsField.setPreferredSize(new Dimension(60, 26));
        colsField.setPreferredSize(new Dimension(60, 26));
        sizePanel.add(rowsLabel);
        sizePanel.add(rowsField);
        sizePanel.add(colsLabel);
        sizePanel.add(colsField);
        newGamePanel.add(sizePanel);

        JPanel rulePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 10));
        rulePanel.setBackground(panelBackground);
        JLabel ruleLabel = new JLabel("Rule:");
        ruleLabel.setFont(labelFont);
        ruleBox.setFont(inputFont);
        ruleBox.setPreferredSize(new Dimension(160, 26));
        rulePanel.add(ruleLabel);
        rulePanel.add(ruleBox);
        newGamePanel.add(rulePanel);

        // Кнопки с отступами
        Dimension btnSize = new Dimension(180, 38);
        startButton.setPreferredSize(btnSize);
        manageRulesButton.setPreferredSize(btnSize);
        exitButton.setPreferredSize(btnSize);
        styleButton(startButton);
        styleButton(manageRulesButton);
        styleButton(exitButton);

        newGamePanel.add(Box.createVerticalStrut(15));
        newGamePanel.add(startButton);
        newGamePanel.add(Box.createVerticalStrut(10));
        newGamePanel.add(manageRulesButton);
        newGamePanel.add(Box.createVerticalStrut(10));
        newGamePanel.add(exitButton);

        centerPanel.add(newGamePanel);

        add(centerPanel, BorderLayout.CENTER);
    }

    private void styleButton(JButton button) {
        button.setFocusPainted(false);
        button.setFont(buttonFont);
        button.setBackground(buttonBg);
        button.setForeground(buttonFg);
        button.setBorder(BorderFactory.createLineBorder(new Color(40, 60, 100), 1));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        // hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(buttonBg.darker());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(buttonBg);
            }
        });
    }

    // Геттеры остаются без изменений
    public JPanel getSaveListPanel() { return saveListPanel; }
    public JTextField getRowsField() { return rowsField; }
    public JTextField getColsField() { return colsField; }
    public JComboBox<String> getRuleBox() { return ruleBox; }
    public JButton getStartButton() { return startButton; }
    public JButton getManageRulesButton() { return manageRulesButton; }
    public JButton getExitButton() { return exitButton; }
    public JButton getManageSavesButton() { return manageSavesButton; }
}
