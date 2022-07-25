package com.example.eventmanager.view.acceptant;

import com.example.eventmanager.controller.AcceptantController;
import com.example.eventmanager.controller.BaseController;
import com.example.eventmanager.model.EventDTO;
import com.example.eventmanager.model.UserDTO;
import com.example.eventmanager.view.BaseScreenHandler;
import com.example.eventmanager.view.FXMLScreenHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class AcceptantItem extends FXMLScreenHandler {

    public static final String OK_RELPY = "OK";
    public static final String DENY_RELPY = "DENY";

    @FXML // fx:id="frame"
    private HBox frame; // Value injected by FXMLLoader

    @FXML // fx:id="username"
    private Label username; // Value injected by FXMLLoader

    @FXML // fx:id="acceptBtn"
    private Button acceptBtn; // Value injected by FXMLLoader

    @FXML // fx:id="rejectBtn"
    private Button rejectBtn; // Value injected by FXMLLoader

    private BaseScreenHandler prevScreen;

    private UserDTO userDTO;

    private EventDTO eventDTO;

    private BaseController controller;

    @FXML
    void accept(ActionEvent event) {
        AcceptantController acceptantController = (AcceptantController) this.controller;
        StringBuilder responseMess = new StringBuilder("");

        if (acceptantController.replyJoinRequest(userDTO, eventDTO, OK_RELPY, responseMess) != 0) {
            getPrevScreen().announceError(responseMess.toString(), "Error");
        } else {
            getPrevScreen().announceInfo("Successfully!", "OK");
        }

        AcceptantScreenHandler acceptantScreenHandler = (AcceptantScreenHandler) getPrevScreen();
        acceptantScreenHandler.updateUserList();

    }

    @FXML
    void reject(ActionEvent event) {
        AcceptantController acceptantController = (AcceptantController) this.controller;
        StringBuilder responseMess = new StringBuilder("");

        if (acceptantController.replyJoinRequest(userDTO, eventDTO, DENY_RELPY, responseMess) != 0) {
            getPrevScreen().announceError(responseMess.toString(), "Error");
        } else {
            getPrevScreen().announceInfo("Successfully!", "OK");
        }

        AcceptantScreenHandler acceptantScreenHandler = (AcceptantScreenHandler) getPrevScreen();
        acceptantScreenHandler.updateUserList();
    }

    public AcceptantItem(String screenPath, UserDTO userDTO, EventDTO eventDTO, BaseController controller) throws IOException {
        super(screenPath);
        username.setText(userDTO.getUsername());
        this.userDTO = userDTO;
        this.eventDTO = eventDTO;
        this.controller = controller;
    }

    public BaseScreenHandler getPrevScreen() {
        return prevScreen;
    }

    public void setPrevScreen(BaseScreenHandler prevScreen) {
        this.prevScreen = prevScreen;
    }

    public HBox getFrame() {
        return frame;
    }

    public void setFrame(HBox frame) {
        this.frame = frame;
    }

}
