package org.example.controller.viewControllers;

import org.example.App;
import org.example.db.BoardSaveRepository;
import org.example.db.CustomRuleRepository;
import org.example.view.ControlsView;

import javax.swing.*;

public class ReturnToMenuController {

    public ReturnToMenuController(ControlsView controlsView) {
        controlsView.getReturnButton().addActionListener(e -> {
            JFrame frame = App.getMainView().getFrame();

            if (frame == null) {
                System.err.println("Main frame is not available.");
                return;
            }

            // Получаем зависимости из контекста Spring
            BoardSaveRepository boardRepo = App.getContext().getBean(BoardSaveRepository.class);
            CustomRuleRepository ruleRepo = App.getContext().getBean(CustomRuleRepository.class);

            // Используем MainMenuController, если он реализован по MVC
            MainMenuController mainMenuController = new MainMenuController(frame, boardRepo, ruleRepo);
            JPanel mainMenuView = mainMenuController.getViewPanel();

            frame.setContentPane(mainMenuView);
            frame.revalidate();
            frame.repaint();
        });
    }
}
