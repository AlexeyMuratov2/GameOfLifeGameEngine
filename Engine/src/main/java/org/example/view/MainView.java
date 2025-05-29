package org.example.view;

import javax.swing.*;
import java.awt.*;

public class MainView {
    private final JFrame frame;
    private final JPanel gamePanel;

    public MainView(GridView gridView, ControlsView controlsView) {
        frame = new JFrame("Conway's Game of Life");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.setLocationRelativeTo(null); // Центрируем окно

        // Собираем игровую панель, но пока не добавляем в frame
        gamePanel = new JPanel(new BorderLayout());
        gamePanel.add(gridView.getPanel(), BorderLayout.CENTER);
        gamePanel.add(controlsView.getPanel(), BorderLayout.SOUTH);
    }

    public JFrame getFrame() {
        return frame;
    }

    public JPanel getMainPanel() {
        return gamePanel;
    }

    // В будущем можно добавить метод для смены содержимого
    public void setContent(JPanel panel) {
        frame.setContentPane(panel);
        frame.revalidate();
        frame.repaint();
    }
}
