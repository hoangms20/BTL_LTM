package com.example.eventmanager.view.eventdetail;

import com.example.eventmanager.model.UserDTO;
import com.example.eventmanager.view.FXMLScreenHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

public class UserItem extends FXMLScreenHandler {
    @FXML // fx:id="username"
    private Label username; // Value injected by FXMLLoader

    public UserItem(String screenPath) throws IOException {
        super(screenPath);
    }

    public UserItem(String screenPath, UserDTO userDTO) throws IOException {
        super(screenPath);
        username.setText(userDTO.getUsername());
    }

}
