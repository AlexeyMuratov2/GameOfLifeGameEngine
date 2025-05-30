package org.example.view;

import javax.swing.*;
import java.awt.*;

public class MainView {
    private final JFrame frame;
    private final JPanel gamePanel;
    private final SaveListPanel saveListPanel;

    public MainView(JFrame frame, GridView gridView, ControlsView controlsView) {
        this.frame = frame;
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setSize(1200, 800);
        this.frame.setLocationRelativeTo(null); // Центрируем окно


        this.saveListPanel = new SaveListPanel();
        gamePanel = new JPanel(new BorderLayout());
        gamePanel.add(gridView.getPanel(), BorderLayout.CENTER);
        gamePanel.add(controlsView.getPanel(), BorderLayout.SOUTH);

        gamePanel.add(saveListPanel, BorderLayout.EAST);
    }

    public JFrame getFrame() {
        return frame;
    }

    public SaveListPanel getSaveListPanel() {
        return saveListPanel;
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
