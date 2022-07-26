package com.example.eventmanager.view;

import java.io.IOException;
import java.net.URL;
import java.util.Hashtable;
import java.util.ResourceBundle;

import com.example.eventmanager.controller.BaseController;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

/**
 * This class is the base screen
 *
 * @author hoangnguyenthe20183925
 */
public class BaseScreenHandler extends FXMLScreenHandler implements Initializable {

    private Scene scene;//scene
    private BaseScreenHandler prev;//previous screen
    protected final Stage stage;//stage
    protected BaseScreenHandler homeScreenHandler;//home screen
    protected Hashtable<String, String> messages;
    private BaseController bController;//controller

    private BaseScreenHandler(String screenPath) throws IOException {
        super(screenPath);
        this.stage = new Stage();
    }

    public void setPreviousScreen(BaseScreenHandler prev) {
        this.prev = prev;
    }

    public BaseScreenHandler getPreviousScreen() {
        return this.prev;
    }

    public BaseScreenHandler(Stage stage, String screenPath) throws IOException {
        super(screenPath);
        this.stage = stage;
    }

    /**
     * This function is to show screen
     */
    public void show() {
        if (this.scene == null) {
            this.scene = new Scene(this.content);
        }
        this.stage.setScene(this.scene);
        this.stage.show();
    }

    public void setScreenTitle(String string) {
        this.stage.setTitle(string);
    }

    public void setBController(BaseController bController) {
        this.bController = bController;
        setupWhenSetBController();
    }

    public BaseController getBController() {
        return this.bController;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setupWhenSetBController() {
    }

    public void setHomeScreenHandler(BaseScreenHandler HomeScreenHandler) {
        this.homeScreenHandler = HomeScreenHandler;
    }

    public BaseScreenHandler getHomeScreenHandler() {
        return homeScreenHandler;
    }

    /**
     * This function show an Aller Announcement
     *
     * @param alert:   Alert to announce
     * @param message: message of announcement
     * @param title:   title of announcement
     */
    public void announceAlert(Alert alert, String message, String title) {
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * This function show an Error Announcement
     *
     * @param message: message of announcement
     * @param title:   title of announcement
     */
    public void announceError(String message, String title) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        announceAlert(alert, message, title);
    }

    /**
     * This function show an Information Announcement
     *
     * @param message: message of announcement
     * @param title:   title of announcement
     */
    public void announceInfo(String message, String title) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        announceAlert(alert, message, title);
    }

    /**
     * This function show an Confirm Announcement
     *
     * @param message: message of announcement
     * @param title:   title of announcement
     * @return : true if choose OK Button else false
     */
    public boolean announceConfirm(String message, String title) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        announceAlert(alert, message, title);

        return alert.getResult().getText().equals("OK");
    }

    /**
     * This function show an Warning Announcement
     *
     * @param message: message of announcement
     * @param title:   title of announcement
     */
    public void announceWarning(String message, String title) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        announceAlert(alert, message, title);
    }

}
