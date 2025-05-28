package org.example.controller;

// controller/SimulationController.java

import org.example.model.*;
import org.example.view.ControlsView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class SimulationController {
    private final Timer timer;

    public SimulationController(GridModel model, ControlsView controls) {
        timer = new Timer(200, e -> model.nextGeneration());

        controls.getStartButton().addActionListener(e -> timer.start());
        controls.getStopButton().addActionListener(e -> timer.stop());
        controls.getStepButton().addActionListener(e -> model.nextGeneration());
    }

    public void stop() {
        timer.stop();
    }
}
