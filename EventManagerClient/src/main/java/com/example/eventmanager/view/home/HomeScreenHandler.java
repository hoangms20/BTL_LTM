package com.example.eventmanager.view.home;

import com.example.eventmanager.constant.ScreenPathConstain;
import com.example.eventmanager.controller.AnnouncementController;
import com.example.eventmanager.controller.CreateEventController;
import com.example.eventmanager.controller.HomeController;
import com.example.eventmanager.subsystem.Client;
import com.example.eventmanager.model.EventDTO;
import com.example.eventmanager.view.BaseScreenHandler;
import com.example.eventmanager.view.announcement.AnnouncementScreenHandler;
import com.example.eventmanager.view.createevent.CreateEventScreenHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * This class is Home screen
 *
 * @author hoangnguyenthe20183925
 */
public class HomeScreenHandler extends BaseScreenHandler implements Initializable {

    @FXML // fx:id="scrollPane"
    private ScrollPane scrollPane; // Value injected by FXMLLoader

    @FXML // fx:id="eventContainer"
    private VBox eventContainer; // Value injected by FXMLLoader

    @FXML // fx:id="logo"
    private ImageView logo; // Value injected by FXMLLoader

    @FXML // fx:id="announceImage"
    private ImageView announceImage; // Value injected by FXMLLoader

    @FXML // fx:id="logoutMenuItem"
    private MenuItem logoutMenuItem; // Value injected by FXMLLoader

    @FXML // fx:id="logoutMenuItem"
    private MenuItem closeMenuItem; // Value injected by FXMLLoader

    @FXML // fx:id="username"
    private Label username; // Value injected by FXMLLoader

    @FXML // fx:id="createEventButton"
    private Button createEventButton; // Value injected by FXMLLoader

    @FXML // fx:id="seeYourEventButton"
    private Button seeYourEventButton; // Value injected by FXMLLoader

    @FXML // fx:id="seeAllEventButton"
    private Button seeAllEventButton; // Value injected by FXMLLoader

    @FXML // fx:id="refreshBtn"
    private Button refreshBtn; // Value injected by FXMLLoader

    private List<EventDTO> eventList;

    public HomeScreenHandler(Stage stage, String screenPath) throws IOException {
        super(stage, screenPath);
    }

    // This method is called by the FXMLLoader when initialization is complete
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.setImage(logo, "images/logo.png");
        super.setImage(announceImage, "images/announceicon.png");

    }

    @Override
    public void show() {
        HomeController controller = (HomeController) getBController();
        username.setText(controller.getUserName());

        updateEventList();
        displayAllEvent();

        super.show();
    }

    public List<EventDTO> getEventList() {
        return this.eventList;
    }

    public void setEventList(List<EventDTO> eventList) {
        this.eventList = eventList;
    }

    private void updateEventList() {
        HomeController controller = (HomeController) getBController();
        StringBuilder responseMess = new StringBuilder("");
        setEventList(controller.getListEvent(responseMess));
    }

    private void displayAllEvent(){
        displayEventList(getEventList());
    }

    private void displayMyEvent(){
        List<EventDTO> eventDTOList = new ArrayList<>();
        List<EventDTO> list = getEventList();
        String username = getBController().getUserName();

        if (list == null)   return;

        for (EventDTO event :
                list) {
            if (event.getCreatedBy().equals(username)) {
                eventDTOList.add(event);
            }
        }

        displayEventList(eventDTOList);
    }

    private void displayEventList(List<EventDTO> list) {
        this.eventContainer.getChildren().clear();

        if (list != null) {
            int i = 0;
            for (EventDTO event :
                    list) {
                try {
                    EventItem eventItem = new EventItem(ScreenPathConstain.EVENT_ITEM_SCREEN_PATH, event);
                    eventItem.setHomeScreenHandler(this);
                    if (i % 2 == 0) {
                        eventItem.getParentVBox().setStyle("-fx-background-color: #FEFBE7");
                    } else {
                        eventItem.getParentVBox().setStyle("-fx-background-color: #C4DDFF");
                    }

                    this.eventContainer.getChildren().add(eventItem.getContent());

                    i++;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @FXML
    public void seeAnnounce(MouseEvent event) throws IOException {
        AnnouncementController controller = new AnnouncementController();
        Stage stage = new Stage();
        stage.setAlwaysOnTop(true);
        AnnouncementScreenHandler announcementScreenHandler = new AnnouncementScreenHandler(stage, ScreenPathConstain.ANNOUNCEMENT_SCREEN_PATH);
        announcementScreenHandler.setScreenTitle("Announcement Screen");
        announcementScreenHandler.setBController(controller);
        announcementScreenHandler.setHomeScreenHandler(this);
        announcementScreenHandler.show();
    }

    @FXML
    public void createEvent(ActionEvent event) throws IOException {
        CreateEventController createEventController = new CreateEventController();
        Stage stage = new Stage();
        CreateEventScreenHandler createEventScreenHandler = new CreateEventScreenHandler(stage, ScreenPathConstain.CREATE_EVENT_SCREEN_PATH);
        createEventScreenHandler.setScreenTitle("Create Event Screen");
        createEventScreenHandler.setBController(createEventController);
        createEventScreenHandler.setHomeScreenHandler(this);
        createEventScreenHandler.show();

    }

    @FXML
    void refreshEventList(ActionEvent event) {
        updateEventList();
        displayAllEvent();
    }

    @FXML
    public void seeYourEvent(ActionEvent event) {
        displayMyEvent();
    }

    @FXML
    public void seeAllEvent(ActionEvent event) {
        displayAllEvent();
    }

    @FXML
    public void logOut(ActionEvent event) {
        HomeController controller = (HomeController) getBController();
        StringBuilder responseMess = new StringBuilder("");
        int ret = controller.logout(responseMess);
        if (ret != 0) {
            announceError(responseMess.toString(), "Error");
            return;
        }
        getPreviousScreen().show();
    }

    @FXML
    public void exit(ActionEvent event) {
        Client.getClient().close();
        this.stage.close();
    }
}
