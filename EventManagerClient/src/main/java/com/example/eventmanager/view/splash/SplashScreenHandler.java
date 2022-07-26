package com.example.eventmanager.view.splash;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * This class is Splash screen
 *
 * @author hoangnguyenthe20183925
 */
public class SplashScreenHandler implements Initializable {

    @FXML // fx:id="logo"
    private ImageView logo; // Value injected by FXMLLoader


    // This method is called by the FXMLLoader when initialization is complete
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //set logo
        File file = new File("images/splash.png");
        Image image = new Image(file.toURI().toString());
        logo.setImage(image);
    }
}
