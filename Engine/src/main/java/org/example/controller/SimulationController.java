package org.example.controller;

import org.example.model.*;
import org.example.view.ControlsView;

import javax.swing.Timer;

public class SimulationController {
    private final Timer timer;
    private final GridModel model;
    private final GameState gameState = GameState.getInstance();

    public SimulationController(GridModel model, ControlsView controls) {
        this.model = model;

        timer = new Timer(200, e -> {
            model.getHistoryManager().save(model.createMemento());
            model.nextGeneration();
        });

        controls.getStartButton().addActionListener(e -> start());
        controls.getStopButton().addActionListener(e -> stop());

        controls.getStepButton().addActionListener(e -> {
            model.getHistoryManager().save(model.createMemento());
            model.nextGeneration();
        });
    }

    public void start() {
        gameState.setRunning(true);
        timer.start();
    }

    public void stop() {
        gameState.setRunning(false);
        timer.stop();
    }
}
