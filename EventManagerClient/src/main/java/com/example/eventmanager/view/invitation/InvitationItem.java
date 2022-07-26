package com.example.eventmanager.view.invitation;

import com.example.eventmanager.controller.BaseController;
import com.example.eventmanager.controller.InvitationController;
import com.example.eventmanager.model.EventDTO;
import com.example.eventmanager.model.UserDTO;
import com.example.eventmanager.view.BaseScreenHandler;
import com.example.eventmanager.view.FXMLScreenHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;

public class InvitationItem extends FXMLScreenHandler {

    @FXML // fx:id="username"
    private Label username; // Value injected by FXMLLoader

    @FXML // fx:id="inviteButton"
    private Button inviteButton; // Value injected by FXMLLoader

    private BaseScreenHandler prevScreen;

    private BaseController controller;

    private UserDTO userDTO;

    private EventDTO eventDTO;

    @FXML
    void invite(ActionEvent event) {
        InvitationController invitationController = (InvitationController) this.controller;
        StringBuilder responseMess = new StringBuilder("");

        if (invitationController.invite(userDTO, eventDTO, responseMess) != 0) {
            getPrevScreen().announceError(responseMess.toString(), "Error");
        } else {
            getPrevScreen().announceInfo("Successfully!", "OK");
            inviteButton.setDisable(true);
        }


    }

    public InvitationItem(String screenPath) throws IOException {
        super(screenPath);
    }

    public InvitationItem(String screenPath, UserDTO userDTO, EventDTO eventDTO, BaseController controller) throws IOException {
        super(screenPath);
        username.setText(userDTO.getUsername());
        this.controller = controller;
        this.userDTO = userDTO;
        this.eventDTO = eventDTO;
    }

    public BaseScreenHandler getPrevScreen() {
        return prevScreen;
    }

    public void setPrevScreen(BaseScreenHandler prevScreen) {
        this.prevScreen = prevScreen;
    }
}
