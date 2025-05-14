package org.example;

import javafx.application.Application;
import javafx.stage.Stage;
import org.example.controller.MainController;
import org.example.view.MainView;

public class App extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        MainView mainView = new MainView();
        MainController mainController = new MainController(mainView);
    }
}
