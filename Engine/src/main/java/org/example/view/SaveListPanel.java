package org.example.view;

import org.example.db.BoardSaveMeta;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SaveListPanel extends JPanel {
    private final JList<BoardSaveMeta> saveJList;
    private final DefaultListModel<BoardSaveMeta> listModel;

    public SaveListPanel() {
        super(new BorderLayout());
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        listModel = new DefaultListModel<>();
        saveJList = new JList<>(listModel);
        saveJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        saveJList.setFont(new Font("SansSerif", Font.PLAIN, 14));
        saveJList.setFixedCellHeight(28);
        saveJList.setCellRenderer(new SaveListCellRenderer());

        JScrollPane scrollPane = new JScrollPane(saveJList);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(180, 180, 180)));

        JLabel titleLabel = new JLabel("Saved Figures:");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        titleLabel.setForeground(Color.DARK_GRAY);

        this.add(titleLabel, BorderLayout.NORTH);
        this.add(scrollPane, BorderLayout.CENTER);
    }

    public void setSaves(List<BoardSaveMeta> saves) {
        listModel.clear();
        for (BoardSaveMeta save : saves) {
            listModel.addElement(save);
        }
    }

    public BoardSaveMeta getSelectedSave() {
        return saveJList.getSelectedValue();
    }

    public void clearSelection() {
        saveJList.clearSelection();
    }

    public JList<BoardSaveMeta> getJList() {
        return saveJList;
    }

    private static class SaveListCellRenderer extends JLabel implements ListCellRenderer<BoardSaveMeta> {
        @Override
        public Component getListCellRendererComponent(JList<? extends BoardSaveMeta> list, BoardSaveMeta value, int index,
                                                      boolean isSelected, boolean cellHasFocus) {
            setText(value.getName() + " (" + value.getRows() + "×" + value.getCols() + ")");
            setOpaque(true);
            setBorder(BorderFactory.createEmptyBorder(4, 8, 4, 8));
            setFont(new Font("SansSerif", Font.PLAIN, 14));

            if (isSelected) {
                setBackground(new Color(200, 220, 240));
                setForeground(Color.DARK_GRAY);
            } else {
                setBackground(Color.WHITE);
                setForeground(Color.BLACK);
            }

            return this;
        }
    }
}
