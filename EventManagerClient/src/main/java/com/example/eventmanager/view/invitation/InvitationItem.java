package com.example.eventmanager.view.invitation;

import com.example.eventmanager.model.UserDTO;
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

    @FXML
    void invite(ActionEvent event) {

    }

    public InvitationItem(String screenPath) throws IOException {
        super(screenPath);
    }

    public InvitationItem(String screenPath, UserDTO userDTO) throws IOException {
        super(screenPath);
        username.setText(userDTO.getUsername());
    }
}
