package com.example.eventmanager;

import com.example.eventmanager.Constain.ScreenPathConstain;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;


public class MainTest extends javafx.application.Application{

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource(ScreenPathConstain.SPLASH_SCREEN_PATH));
        StackPane root = (StackPane) fxmlLoader.load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("title");
        alert.setContentText("message");
        alert.showAndWait();
        System.out.println(alert.getResult().getText());

    }

    public static void main(String[] args) {
        launch(args);
    }




}
