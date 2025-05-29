package org.example.view;

import javax.swing.*;
import java.awt.*;

public class MainView {
    private final JFrame frame;

    public MainView(GridView gridView, ControlsView controlsView) {
        frame = new JFrame("Conway's Game of Life");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        frame.add(gridView.getPanel(), BorderLayout.CENTER);
        frame.add(controlsView.getPanel(), BorderLayout.SOUTH);

        frame.setSize(800, 800);
        frame.setVisible(true);
    }

    public JFrame getFrame() {
        return frame;
    }
}
