//package org.example.controller;
// TODO
//
//import org.example.model.GridModel;
//import org.example.view.GridView;
//
//import javax.swing.*;
//import java.awt.event.MouseAdapter;
//import java.awt.event.MouseEvent;
//
//public class GridController {
//
//    private final GridModel model;
//    private final GridView view;
//
//    // Сохраняем, в какое состояние переводим при drag
//    private Boolean drawState = null;
//
//    public GridController(GridModel model, GridView view) {
//        this.model = model;
//        this.view = view;
//
//        setupMouseListeners();
//    }
//
//    private void setupMouseListeners() {
//        int rows = model.getRowCount();
//        int cols = model.getColCount();
//
//        // Устанавливаем обработчик для каждой клетки
//        for (int y = 0; y < rows; y++) {
//            for (int x = 0; x < cols; x++) {
//                int row = y;
//                int col = x;
//
//                JPanel cellPanel = view.getCellPanel(row, col);
//
//                cellPanel.addMouseListener(new MouseAdapter() {
//                    @Override
//                    public void mousePressed(MouseEvent e) {
//                        boolean currentState = model.isCellAlive(row, col);
//                        drawState = !currentState; // Переводим в противоположное
//                        model.setCell(row, col, drawState);
//                    }
//
//                    @Override
//                    public void mouseReleased(MouseEvent e) {
//                        drawState = null;
//                    }
//                });
//
//                cellPanel.addMouseMotionListener(new MouseAdapter() {
//                    @Override
//                    public void mouseEntered(MouseEvent e) {
//                        if (drawState != null) {
//                            model.setCell(row, col, drawState);
//                        }
//                    }
//                });
//            }
//        }
//    }
//}
