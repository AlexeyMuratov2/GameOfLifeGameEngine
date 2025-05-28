package org.example.controller;


import org.example.model.*;
import org.example.view.ControlsView;

import javax.swing.Timer;

public class SimulationController {
    private final Timer timer;
    private GameState gameState = GameState.getInstance();

    public SimulationController(GridModel model, ControlsView controls) {
        timer = new Timer(200, e -> model.nextGeneration());

        controls.getStartButton().addActionListener(e -> start());
        controls.getStopButton().addActionListener(e -> stop());
        controls.getStepButton().addActionListener(e -> model.nextGeneration());
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
