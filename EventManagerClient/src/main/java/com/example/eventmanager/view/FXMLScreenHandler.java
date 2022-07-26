package com.example.eventmanager.view;

import java.io.File;
import java.io.IOException;

import com.example.eventmanager.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * This class is to set FXML screen
 *
 * @author hoangnguyenthe20183925
 */
public class FXMLScreenHandler {

    protected FXMLLoader loader;//load FXML
    protected Parent content;//parent

    /**
     * this function load variable
     *
     * @param screenPath: path fxml file
     */
    public FXMLScreenHandler(String screenPath) throws IOException {
        this.loader = new FXMLLoader(Application.class.getResource(screenPath));
        // Set this class as the controller
        this.loader.setController(this);
        this.content = loader.load();
    }

    public Parent getContent() {
        return this.content;
    }

    public FXMLLoader getLoader() {
        return this.loader;
    }

    /**
     * this function set ImageView
     *
     * @param imv:  ImageView
     * @param path: path of image
     */
    public void setImage(ImageView imv, String path) {
        File file = new File(path);
        Image img = new Image(file.toURI().toString());
        imv.setImage(img);
    }
}
