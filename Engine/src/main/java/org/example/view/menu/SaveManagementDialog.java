package org.example.view.menu;

import org.example.db.BoardSaveMeta;
import org.example.db.BoardSaveRepository;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SaveManagementDialog extends JDialog {
    public SaveManagementDialog(JFrame parent, BoardSaveRepository repository, DefaultListModel<BoardSaveMeta> saveListModel) {
        super(parent, "Manage Saved Games", true);
        setSize(500, 350);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        JList<BoardSaveMeta> saveList = new JList<>(saveListModel);
        saveList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        add(new JScrollPane(saveList), BorderLayout.CENTER);

        JButton deleteButton = new JButton("Delete Selected Save");
        deleteButton.addActionListener(e -> {
            BoardSaveMeta selected = saveList.getSelectedValue();
            if (selected != null) {
                int confirm = JOptionPane.showConfirmDialog(this,
                        "Delete save \"" + selected.getName() + "\"?", "Confirm Deletion",
                        JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    repository.deleteSaveById(selected.getId());
                    saveListModel.removeElement(selected);
                }
            }
        });

        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> dispose());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(deleteButton);
        buttonPanel.add(closeButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }
}

