package org.example;

import org.example.controller.*;
import org.example.controller.viewControllers.MainMenuController;
import org.example.controller.viewControllers.ReturnToMenuController;
import org.example.db.*;
import org.example.model.*;
import org.example.rules.*;
import org.example.view.*;
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
    private static SaveListPanel saveListPanel;
    private static JFrame frame;


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            context = new AnnotationConfigApplicationContext(AppConfig.class);

            CustomRuleRepository customRuleRepo = context.getBean(CustomRuleRepository.class);
            BoardSaveRepository boardSaveRepo = context.getBean(BoardSaveRepository.class);

            RuleFactory.init(customRuleRepo);

            frame = new JFrame("Game of Life");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);

            MainMenuController menuController = new MainMenuController(frame, boardSaveRepo, customRuleRepo);
            frame.setContentPane(menuController.getViewPanel());

            frame.setVisible(true);
        });
    }

    public static void startGame(int rows, int cols, String ruleName, List<int[]> liveCells) {
        Rule rule = RuleFactory.getInstance().fromString(ruleName);

        // Создаём модель и вью с актуальными размерами
        model = new GridModel(rows, cols, rule);
        gridView = new GridView(rows, cols);
        controlsView = new ControlsView();

        mainView = new MainView(frame, gridView, controlsView);

        JFrame frame = mainView.getFrame();

        // Контроллеры (создаются только после создания всех view/model)
        CustomRuleRepository customRuleRepo = context.getBean(CustomRuleRepository.class);
        BoardSaveRepository boardSaveRepo = context.getBean(BoardSaveRepository.class);

        RuleEditorView ruleEditorView = new RuleEditorView(frame);

        new SimulationController(model, controlsView);
        new ControlsController(model, controlsView, customRuleRepo);
        new GridClickController(model, gridView);
        new RuleController(model, ruleEditorView, controlsView, customRuleRepo);
        new SaveController(model, controlsView, frame, boardSaveRepo, mainView.getSaveListPanel());
        new ReturnToMenuController(controlsView);
        new UndoController(model, controlsView);

        model.addObserver(gridView);

        // Загружаем клетки, если они есть
        for (int[] cell : liveCells) {
            model.setCell(cell[0], cell[1], true);
        }

        frame.setContentPane(mainView.getMainPanel());
        frame.revalidate();
        frame.repaint();

        frame.setVisible(true);
        model.notifyObservers();

        SaveListPanel saveListPanel = mainView.getSaveListPanel();
        List<BoardSaveMeta> saves = boardSaveRepo.listAllSaves();
        saveListPanel.setSaves(saves);
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

