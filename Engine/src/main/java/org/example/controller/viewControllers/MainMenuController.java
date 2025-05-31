package org.example.controller.viewControllers;

import org.example.App;
import org.example.db.BoardSaveMeta;
import org.example.db.BoardSaveRepository;
import org.example.db.CustomRuleRepository;
import org.example.view.menu.MainMenuPanel;
import org.example.view.menu.RuleManagementDialog;
import org.example.view.menu.SaveManagementDialog;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MainMenuController {
    private final JFrame frame;
    private final BoardSaveRepository boardSaveRepository;
    private final CustomRuleRepository ruleRepository;
    private final MainMenuPanel view;

    public MainMenuController(JFrame frame, BoardSaveRepository boardSaveRepository, CustomRuleRepository ruleRepository) {
        this.frame = frame;
        this.boardSaveRepository = boardSaveRepository;
        this.ruleRepository = ruleRepository;
        this.view = new MainMenuPanel();

        initListeners();
        loadRules();
        loadSavedGames();
    }

    private void initListeners() {
        view.getStartButton().addActionListener(e -> {
            int rows = Integer.parseInt(view.getRowsField().getText());
            int cols = Integer.parseInt(view.getColsField().getText());
            String rule = (String) view.getRuleBox().getSelectedItem();
            App.startGame(rows, cols, rule);
        });

        view.getManageRulesButton().addActionListener(e -> {
            RuleManagementDialog dialog = new RuleManagementDialog(frame, ruleRepository, view.getRuleBox());
            dialog.setVisible(true);
        });

        view.getExitButton().addActionListener(e -> System.exit(0));

        view.getManageSavesButton().addActionListener(e -> openSaveManagementDialog());
    }

    private void openSaveManagementDialog() {
        DefaultListModel<BoardSaveMeta> saveListModel = new DefaultListModel<>();
        for (BoardSaveMeta save : boardSaveRepository.listAllSaves()) {
            saveListModel.addElement(save);
        }

        SaveManagementDialog dialog = new SaveManagementDialog(frame, boardSaveRepository, saveListModel);
        dialog.setVisible(true);

        loadSavedGames();
    }



    private void loadRules() {
        view.getRuleBox().addItem("Game of Life");
        view.getRuleBox().addItem("HighLife");
        view.getRuleBox().addItem("Day & Night");
        view.getRuleBox().addItem("Seeds");
        ruleRepository.findAllCustomRules().forEach(view.getRuleBox()::addItem);
    }

    private void loadSavedGames() {
        JPanel saveListPanel = view.getSaveListPanel();
        saveListPanel.removeAll();

        List<BoardSaveMeta> saves = boardSaveRepository.listAllSaves();
        for (BoardSaveMeta save : saves) {
            JPanel row = new JPanel(new FlowLayout(FlowLayout.LEFT));
            row.add(new JLabel(String.format("Name: %s, Size: %dx%d, Rule: %s", save.getName(), save.getRows(), save.getCols(), save.getRuleName())));

            JButton playButton = new JButton("Play");
            playButton.addActionListener(e -> {
                List<int[]> liveCells = boardSaveRepository.loadLiveCellsBySaveId(save.getId());
                App.startGame(save.getRows(), save.getCols(), save.getRuleName(), liveCells);
            });

            row.add(playButton);
            saveListPanel.add(row);
        }

        saveListPanel.revalidate();
        saveListPanel.repaint();
    }

    public JPanel getViewPanel() {
        return view;
    }
}
