package org.example;

import org.example.controller.*;
import org.example.db.*;
import org.example.model.*;
import org.example.rules.*;
import org.example.view.*;
import org.example.view.menu.MainMenuPanel;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.ApplicationContext;

import javax.swing.*;
import java.util.List;

public class App {
    private static ApplicationContext context;
    private static GridModel model;
    private static GridView gridView;
    private static ControlsView controlsView;
    private static MainView mainView;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            context = new AnnotationConfigApplicationContext(AppConfig.class);

            CustomRuleRepository customRuleRepo = context.getBean(CustomRuleRepository.class);
            BoardSaveRepository boardSaveRepo = context.getBean(BoardSaveRepository.class);

            RuleFactory.init(customRuleRepo);

            Rule initialRule = new GameOfLifeRules();
            model = new GridModel(30, 30, initialRule);

            gridView = new GridView(30, 30);
            controlsView = new ControlsView();
            mainView = new MainView(gridView, controlsView);
            JFrame frame = mainView.getFrame();
            RuleEditorView ruleEditorView = new RuleEditorView(frame);

            MainMenuPanel mainMenuPanel = new MainMenuPanel(frame, boardSaveRepo, customRuleRepo);
            frame.setContentPane(mainMenuPanel);
            frame.revalidate();

            new SimulationController(model, controlsView);
            new ControlsController(model, controlsView, customRuleRepo);
            new GridClickController(model, gridView);
            new RuleController(model, ruleEditorView, controlsView, customRuleRepo);
            new SaveController(model, controlsView, frame, boardSaveRepo);
            new ReturnToMenuController(controlsView);

            model.addObserver(gridView);

            frame.setVisible(true);

        });
    }

    public static void startGame(int rows, int cols, String ruleName, List<int[]> liveCells) {
        Rule rule = RuleFactory.getInstance().fromString(ruleName);
        model.reset(rows, cols, rule);
        for (int[] cell : liveCells) {
            model.setCell(cell[0], cell[1], true);
        }
        gridView.rebuild(rows, cols);
        mainView.getFrame().setContentPane(mainView.getMainPanel());
        mainView.getFrame().revalidate();
        model.notifyObservers();
    }

    public static void startGame(int rows, int cols, String ruleName) {
        startGame(rows, cols, ruleName, List.of());
    }


    public static ApplicationContext getContext() {
        return context;
    }

    public static MainView getMainView() {
        return mainView;
    }
}

