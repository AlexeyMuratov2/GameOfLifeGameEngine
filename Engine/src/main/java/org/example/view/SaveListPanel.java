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

        listModel = new DefaultListModel<>();
        saveJList = new JList<>(listModel);
        saveJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        saveJList.setCellRenderer(new SaveListCellRenderer());


        JScrollPane scrollPane = new JScrollPane(saveJList);
        this.add(new JLabel("Saved figures:"), BorderLayout.NORTH);
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

    // Кастомный рендер для отображения с подсветкой
    private static class SaveListCellRenderer extends JLabel implements ListCellRenderer<BoardSaveMeta> {
        @Override
        public Component getListCellRendererComponent(JList<? extends BoardSaveMeta> list, BoardSaveMeta value, int index,
                                                      boolean isSelected, boolean cellHasFocus) {
            setText(value.getName() + " (" + value.getRows() + "x" + value.getCols() + ")");
            setOpaque(true);
            setFont(new Font("SansSerif", Font.PLAIN, 14));
            setBorder(BorderFactory.createEmptyBorder(4, 8, 4, 8));

            if (isSelected) {
                setBackground(Color.LIGHT_GRAY);
                setForeground(Color.BLACK);
            } else {
                setBackground(Color.WHITE);
                setForeground(Color.DARK_GRAY);
            }

            return this;
        }
    }
}
