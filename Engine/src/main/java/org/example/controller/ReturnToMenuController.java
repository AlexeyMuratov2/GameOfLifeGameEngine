package org.example.controller;

import org.example.view.ControlsView;


import org.example.App;
import org.example.db.BoardSaveRepository;
import org.example.db.CustomRuleRepository;
import org.example.view.menu.MainMenuPanel;

import javax.swing.*;

public class ReturnToMenuController {
    public ReturnToMenuController(ControlsView controlsView) {
        controlsView.getReturnButton().addActionListener(e -> {
            JFrame frame = App.getMainView().getFrame();

            // Получаем репозитории из контекста
            BoardSaveRepository boardRepo = App.getContext().getBean(BoardSaveRepository.class);
            CustomRuleRepository ruleRepo = App.getContext().getBean(CustomRuleRepository.class);

            // Создаем панель меню и устанавливаем в frame
            MainMenuPanel mainMenu = new MainMenuPanel(frame, boardRepo, ruleRepo);
            frame.setContentPane(mainMenu);
            frame.revalidate();
            frame.repaint();
        });
    }
}
