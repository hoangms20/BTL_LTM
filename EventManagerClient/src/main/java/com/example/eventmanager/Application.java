package com.example.eventmanager;

import com.example.eventmanager.controller.LoginController;
import com.example.eventmanager.Constain.ScreenPathConstain;
import com.example.eventmanager.subsystem.Client;
import com.example.eventmanager.view.login.LoginScreenHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    public void announceErrorSomethingWrong() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Connect To Server!");
        alert.setContentText("Something Wrong!");
        alert.showAndWait();
    }
    @Override
    public void start(Stage primaryStage) throws IOException {
        try {
//            // initialize the scene
//            FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource(ScreenPathConstain.SPLASH_SCREEN_PATH));
//            StackPane root = (StackPane) fxmlLoader.load();
//            Scene scene = new Scene(root);
//            primaryStage.setScene(scene);
//            primaryStage.show();
//
//            // Load splash screen with fade in effect
//            FadeTransition fadeIn = new FadeTransition(Duration.seconds(2), root);
//            fadeIn.setFromValue(0);
//            fadeIn.setToValue(1);
//            fadeIn.setCycleCount(1);
//
//            // Finish splash with fade out effect
//            FadeTransition fadeOut = new FadeTransition(Duration.seconds(1), root);
//            fadeOut.setFromValue(1);
//            fadeOut.setToValue(0);
//            fadeOut.setCycleCount(1);
//
//            // After fade in, start fade out
//            fadeIn.play();
//            fadeIn.setOnFinished((e) -> {
//                fadeOut.play();
//            });
//
//            // After fade out, load actual content
//            fadeOut.setOnFinished((e) -> {
                int rs = 0;
                try {
                    Client client = Client.getClient();
                    rs = client.connect();
                    if (rs == 0) {
                        LoginController loginController = new LoginController();
                        LoginScreenHandler loginScreenHandler = new LoginScreenHandler(primaryStage, ScreenPathConstain.LOGIN_SCREEN_PATH);
                        loginScreenHandler.setScreenTitle("Login Screen");
                        loginScreenHandler.setBController(loginController);
                        loginScreenHandler.show();
                    }else {
                        FXMLLoader fx = new FXMLLoader(Application.class.getResource(ScreenPathConstain.ERROR_CONNECT_SERVER_SCREEN_PATH));
                        Pane root1 = (Pane) fx.load();
                        Scene scene1 = new Scene(root1);
                        primaryStage.setScene(scene1);
                        primaryStage.show();
                    }
                } catch (Exception e1) {
                    e1.printStackTrace();
                    announceErrorSomethingWrong();
                }

//            });
        } catch (Exception e) {
            e.printStackTrace();
            announceErrorSomethingWrong();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}