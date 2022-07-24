package com.example.eventmanager;

import com.example.eventmanager.controller.CreateEventController;
import com.example.eventmanager.controller.EventDetailController;
import com.example.eventmanager.controller.HomeController;
import com.example.eventmanager.view.createevent.CreateEventScreenHandler;
import com.example.eventmanager.view.eventdetail.EventDetailScreenHandler;
import com.example.eventmanager.view.home.HomeScreenHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends javafx.application.Application {

    @Override
    public void start(Stage primaryStage) throws IOException {

        try {
//            FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource(Configs.LOGIN_SCREEN_PATH));
//            Parent root = (Parent) fxmlLoader.load();
//            Scene scene = new Scene(root);
//            primaryStage.setTitle("Hello!");
//            primaryStage.setScene(scene);
//            primaryStage.show();

//            HomeController controller = new HomeController();
//            HomeScreenHandler screen = new HomeScreenHandler(primaryStage, "home.fxml");
//            screen.setBController(controller);
//            screen.show();

//            EventDetailController controller = new EventDetailController();
//            EventDetailScreenHandler screen = new EventDetailScreenHandler(primaryStage, "eventdetail.fxml");
//            screen.setBController(controller);
//            screen.show();

            CreateEventController controller = new CreateEventController();
            CreateEventScreenHandler screen = new CreateEventScreenHandler(primaryStage, "createeventform.fxml");
            screen.setBController(controller);
            screen.show();



        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}