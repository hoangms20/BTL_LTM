package com.example.eventmanager.view.createevent;

import com.example.eventmanager.controller.CreateEventController;
import com.example.eventmanager.controller.SignUpController;
import com.example.eventmanager.model.EventDTO;
import com.example.eventmanager.view.BaseScreenHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

public class CreateEventScreenHandler extends BaseScreenHandler {

    @FXML // fx:id="eventName"
    private TextField eventName; // Value injected by FXMLLoader

    @FXML // fx:id="place"
    private TextField place; // Value injected by FXMLLoader

    @FXML // fx:id="description"
    private TextArea description; // Value injected by FXMLLoader

    @FXML // fx:id="time"
    private DatePicker time; // Value injected by FXMLLoader

    @FXML // fx:id="submitButton"
    private Button submitButton; // Value injected by FXMLLoader

    @FXML // fx:id="cancelButton"
    private Button cancelButton; // Value injected by FXMLLoader

    @FXML
    void cancel(ActionEvent event) {
        this.stage.close();
    }

    @FXML
    void submit(ActionEvent event) {
        if (!invalidFields())
            return;

        EventDTO eventDTO = new EventDTO();

        eventDTO.setName(eventName.getText());
        eventDTO.setTime(time.getValue().toString());
        eventDTO.setPlace(place.getText());
        eventDTO.setDescription(description.getText());
        eventDTO.setCreatedBy(getBController().getUserName());

        CreateEventController controller = (CreateEventController) getBController();
        StringBuilder responseMess = new StringBuilder("");
        int ret = controller.createNewEvent(eventDTO, responseMess);

        if (ret != 0 ){
            announceError(responseMess.toString(), "Error");
        }else {
            announceInfo("Create New Event Successfully!", "OK");
            clearFields();
        }

    }

    public CreateEventScreenHandler(Stage stage, String screenPath) throws IOException {
        super(stage, screenPath);
    }

    private boolean invalidFields() {
        if (eventName.getText() == null || eventName.getText().equals("")) {
            announceWarning("Please Enter Event Name!", "Warning");
            return false;
        }

        if (time.getValue() == null || time.getValue().toString().equals("")) {
            announceWarning("Please Enter Time!", "Warning");
            return false;
        }

        if (place.getText() == null || place.getText().equals("")) {
            announceWarning("Please Enter Place!", "Warning");
            return false;
        }

        if (description.getText() == null || description.getText().equals("")) {
            announceWarning("Please Enter Description!", "Warning");
            return false;
        }

        CreateEventController controller = (CreateEventController) getBController();

        if (!controller.checkNotAllowedCharacter(eventName.getText())){
            announceWarning("Fields can not include Forbidden Character!", "Warning");
            return false;
        }

        if (!controller.checkNotAllowedCharacter(place.getText())){
            announceWarning("Fields can not include Forbidden Character!", "Warning");
            return false;
        }

        if (!controller.checkNotAllowedCharacter(description.getText())){
            announceWarning("Fields can not include Forbidden Character!", "Warning");
            return false;
        }

        if (!controller.checkNotAllowedCharacter(time.getValue().toString())){
            announceWarning("Fields can not include Forbidden Character!", "Warning");
            return false;
        }

        //DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate now = LocalDate.now();

        if (!now.isBefore(time.getValue())) {
            announceWarning("Time must start from tomorrow at least!", "Error");
            return false;
        }

        return true;
    }

    private void clearFields() {
        eventName.clear();
        place.clear();
        description.clear();
    }
}
