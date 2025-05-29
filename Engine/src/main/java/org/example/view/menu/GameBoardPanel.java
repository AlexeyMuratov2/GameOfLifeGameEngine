package org.example.view.menu;


import org.example.db.BoardSaveMeta;
import org.example.db.BoardSaveRepository;
import org.example.App;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GameBoardPanel extends JPanel {
    private final JFrame frame;
    private final int rows;
    private final int cols;
    private final String ruleName;
    private final Set<Point> liveCells;

    public GameBoardPanel(JFrame frame, BoardSaveRepository repository, int saveId) {
        this.frame = frame;
        BoardSaveMeta meta = repository.loadBoardMetaById(saveId);
        this.rows = meta.getRows();
        this.cols = meta.getCols();
        this.ruleName = meta.getRuleName();
        this.liveCells = new HashSet<>();
        for (int[] cell : repository.loadLiveCellsBySaveId(saveId)) {
            liveCells.add(new Point(cell[1], cell[0])); // col = x, row = y
        }
        initUI();
    }

    public GameBoardPanel(JFrame frame, BoardSaveRepository repository, int rows, int cols, String ruleName) {
        this.frame = frame;
        this.rows = rows;
        this.cols = cols;
        this.ruleName = ruleName;
        this.liveCells = new HashSet<>();
        initUI();
    }

    private void initUI() {
        setLayout(new BorderLayout());

        JPanel gridPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                int cellSize = Math.min(getWidth() / cols, getHeight() / rows);
                for (int y = 0; y < rows; y++) {
                    for (int x = 0; x < cols; x++) {
                        if (liveCells.contains(new Point(x, y))) {
                            g.setColor(Color.BLACK);
                            g.fillRect(x * cellSize, y * cellSize, cellSize, cellSize);
                        }
                        g.setColor(Color.GRAY);
                        g.drawRect(x * cellSize, y * cellSize, cellSize, cellSize);
                    }
                }
            }

            @Override
            public Dimension getPreferredSize() {
                return new Dimension(cols * 20, rows * 20);
            }
        };

        add(new JScrollPane(gridPanel), BorderLayout.CENTER);

        JButton backButton = new JButton("Back to Menu");
        backButton.addActionListener(e -> {
            MainMenuPanel menu = new MainMenuPanel(frame,
                    App.getContext().getBean(BoardSaveRepository.class),
                    App.getContext().getBean(org.example.db.CustomRuleRepository.class));
            frame.setContentPane(menu);
            frame.revalidate();
        });

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.add(new JLabel("Rule: " + ruleName));
        bottomPanel.add(backButton);
        add(bottomPanel, BorderLayout.SOUTH);
    }
}
