package org.example;

import org.example.controller.*;
import org.example.db.AppConfig;
import org.example.db.CustomRuleRepository;
import org.example.model.*;
import org.example.rules.GameOfLifeRules;
import org.example.rules.Rule;
import org.example.rules.RuleFactory;
import org.example.view.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.swing.*;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            var context = new AnnotationConfigApplicationContext(AppConfig.class);

            CustomRuleRepository customRuleRepo = context.getBean(CustomRuleRepository.class);

            Rule rule = new GameOfLifeRules();
            GridModel model = new GridModel(30, 30, rule);

            RuleFactory.init(customRuleRepo);

            GridView gridView = new GridView(30, 30);
            ControlsView controlsView = new ControlsView();

            MainView mainView = new MainView(gridView, controlsView);
            JFrame frame = mainView.getFrame();

            RuleEditorView ruleEditorView = new RuleEditorView(frame);

            new SimulationController(model, controlsView);
            new ControlsController(model, controlsView, customRuleRepo);
            new GridClickController(model, gridView);
            new RuleController(model, ruleEditorView, controlsView, customRuleRepo);

            model.addObserver(gridView);
        });
    }
}
