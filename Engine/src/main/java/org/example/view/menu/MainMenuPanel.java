package org.example.view.menu;

import org.example.App;
import org.example.db.BoardSaveMeta;
import org.example.db.BoardSaveRepository;
import org.example.db.CustomRuleRepository;
import org.example.model.GridModel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MainMenuPanel extends JPanel {
    private final BoardSaveRepository boardSaveRepository;
    private final CustomRuleRepository ruleRepository;
    private final JFrame frame;

    public MainMenuPanel(JFrame frame, BoardSaveRepository boardSaveRepository, CustomRuleRepository ruleRepository) {
        this.frame = frame;
        this.boardSaveRepository = boardSaveRepository;
        this.ruleRepository = ruleRepository;
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        initUI();
    }

    private void initUI() {
        JLabel title = new JLabel("Game of Life - Main Menu", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 26));
        title.setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 10));
        add(title, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridLayout(1, 2, 10, 10));

        // Панель со списком сохранений
        JPanel saveListPanel = new JPanel();
        saveListPanel.setLayout(new BoxLayout(saveListPanel, BoxLayout.Y_AXIS));
        saveListPanel.setBorder(BorderFactory.createTitledBorder("Saved Games"));

        List<BoardSaveMeta> saves = boardSaveRepository.listAllSaves();
        for (BoardSaveMeta save : saves) {
            JPanel row = new JPanel(new FlowLayout(FlowLayout.LEFT));
            row.add(new JLabel(String.format("Name: %s, Size: %dx%d, Rule: %s", save.getName(), save.getRows(), save.getCols(), save.getRuleName())));
            JButton playButton = new JButton("Play");
            playButton.addActionListener(e -> {
                System.out.println(save.getRows() + " " + save.getCols() + " " + save.getRuleName());
                App.startGame(save.getRows(), save.getCols(), save.getRuleName());
            });
            row.add(playButton);
            saveListPanel.add(row);
        }
        JScrollPane scrollPane = new JScrollPane(saveListPanel);
        centerPanel.add(scrollPane);

        // Панель новой игры
        JPanel newGamePanel = new JPanel();
        newGamePanel.setLayout(new BoxLayout(newGamePanel, BoxLayout.Y_AXIS));
        newGamePanel.setBorder(BorderFactory.createTitledBorder("New Game"));

        JTextField rowsField = new JTextField("20", 5);
        JTextField colsField = new JTextField("20", 5);
        JComboBox<String> ruleBox = new JComboBox<>();

        ruleBox.addItem("Game of Life");
        ruleBox.addItem("HighLife");
        ruleBox.addItem("Day & Night");
        ruleBox.addItem("Seeds");
        ruleRepository.findAllCustomRules().forEach(ruleBox::addItem);

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

        JButton startButton = new JButton("Start New Game");
        startButton.addActionListener(e -> {
            int rows = Integer.parseInt(rowsField.getText());
            int cols = Integer.parseInt(colsField.getText());
            String rule = (String) ruleBox.getSelectedItem();
            App.startGame(rows, cols, rule);
        });
        newGamePanel.add(startButton);

        JButton manageRules = new JButton("Manage Rules");
        manageRules.addActionListener(e -> {
            RuleManagementDialog dialog = new RuleManagementDialog(frame, ruleRepository, ruleBox);
            dialog.setVisible(true);
        });

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> System.exit(0));

        newGamePanel.add(manageRules);
        newGamePanel.add(exitButton);

        centerPanel.add(newGamePanel);
        add(centerPanel, BorderLayout.CENTER);
    }
}
