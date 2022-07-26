package com.example.eventmanager.view.home;

import com.example.eventmanager.Constain.ScreenPathConstain;
import com.example.eventmanager.controller.EventDetailController;
import com.example.eventmanager.model.EventDTO;
import com.example.eventmanager.view.BaseScreenHandler;
import com.example.eventmanager.view.FXMLScreenHandler;
import com.example.eventmanager.view.eventdetail.EventDetailScreenHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * This class is item of home screen
 *
 * @author hoangnguyenthe20183925
 */
public class EventItem extends FXMLScreenHandler {

    private EventDTO event;

    @FXML // fx:id="parentVBox"
    private VBox parentVBox; // Value injected by FXMLLoader

    @FXML // fx:id="eventId"
    private Label eventId; // Value injected by FXMLLoader

    @FXML // fx:id="eventName"
    private Label eventName; // Value injected by FXMLLoader

    @FXML // fx:id="userName"
    private Label createdBy; // Value injected by FXMLLoader

    @FXML // fx:id="time"
    private Label time; // Value injected by FXMLLoader

    @FXML // fx:id="place"
    private Label place; // Value injected by FXMLLoader

    @FXML // fx:id="detailButton"
    private Button detailButton; // Value injected by FXMLLoader

    private EventDTO eventDTO;

    protected BaseScreenHandler homeScreenHandler;

    public EventItem(String screenPath) throws IOException {
        super(screenPath);
    }

    public EventItem(String screenPath, EventDTO eventEntity) throws IOException {
        super(screenPath);
        setEvent(eventEntity);
    }

    public void setEvent(EventDTO event) {
        this.eventDTO = event;
        eventId.setText(event.getId());
        eventName.setText(event.getName());
        time.setText(event.getTime());
        createdBy.setText(event.getCreatedBy());
        place.setText(event.getPlace());
    }

    public BaseScreenHandler getHomeScreenHandler() {
        return homeScreenHandler;
    }

    public void setHomeScreenHandler(BaseScreenHandler homeScreenHandler) {
        this.homeScreenHandler = homeScreenHandler;
    }

    public VBox getParentVBox() {
        return parentVBox;
    }

    @FXML
    void watchDetail(ActionEvent event) throws IOException {
        EventDetailController eventDetailController = new EventDetailController();
        EventDetailScreenHandler eventDetailScreenHandler = new EventDetailScreenHandler(new Stage(),
                ScreenPathConstain.EVENT_DETAIL_SCREEN_PATH, eventDTO);
        eventDetailScreenHandler.setScreenTitle("Event Detail");
        eventDetailScreenHandler.setBController(eventDetailController);
        eventDetailScreenHandler.setHomeScreenHandler(getHomeScreenHandler());
        eventDetailScreenHandler.show();
    }

}
