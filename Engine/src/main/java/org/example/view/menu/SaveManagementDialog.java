package org.example.view.menu;

import org.example.db.BoardSaveMeta;
import org.example.db.BoardSaveRepository;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class SaveManagementDialog extends JDialog {
    private final Color backgroundColor = new Color(245, 247, 250);
    private final Color panelBackground = Color.WHITE;
    private final Color borderColor = new Color(180, 180, 180);
    private final Color buttonBg = new Color(60, 85, 130);
    private final Color buttonFg = Color.WHITE;
    private final Font labelFont = new Font("Serif", Font.PLAIN, 15);
    private final Font buttonFont = new Font("Serif", Font.BOLD, 14);

    public SaveManagementDialog(JFrame parent, BoardSaveRepository repository, DefaultListModel<BoardSaveMeta> saveListModel) {
        super(parent, "Manage Saved Games", true);
        setSize(520, 380);
        setLocationRelativeTo(parent);
        getContentPane().setBackground(backgroundColor);
        setLayout(new BorderLayout(10, 10));
        setResizable(false);

        JList<BoardSaveMeta> saveList = new JList<>(saveListModel);
        saveList.setFont(labelFont);
        saveList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(saveList);
        scrollPane.setBorder(new CompoundBorder(
                new LineBorder(borderColor, 1),
                new EmptyBorder(5,5,5,5)
        ));
        add(scrollPane, BorderLayout.CENTER);

        JButton deleteButton = new JButton("Delete Selected Save");
        styleButton(deleteButton);
        deleteButton.setPreferredSize(new Dimension(180, 36));
        deleteButton.addActionListener(e -> {
            BoardSaveMeta selected = saveList.getSelectedValue();
            if (selected != null) {
                int confirm = JOptionPane.showConfirmDialog(this,
                        "Delete save \"" + selected.getName() + "\"?",
                        "Confirm Deletion",
                        JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    repository.deleteSaveById(selected.getId());
                    saveListModel.removeElement(selected);
                }
            }
        });

        JButton closeButton = new JButton("Close");
        styleButton(closeButton);
        closeButton.setPreferredSize(new Dimension(90, 36));
        closeButton.addActionListener(e -> dispose());

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 10));
        buttonPanel.setBackground(panelBackground);
        buttonPanel.add(deleteButton);
        buttonPanel.add(closeButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void styleButton(JButton button) {
        button.setFocusPainted(false);
        button.setFont(buttonFont);
        button.setBackground(buttonBg);
        button.setForeground(buttonFg);
        button.setBorder(BorderFactory.createLineBorder(new Color(40, 60, 100), 1));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(buttonBg.darker());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(buttonBg);
            }
        });
    }
}
