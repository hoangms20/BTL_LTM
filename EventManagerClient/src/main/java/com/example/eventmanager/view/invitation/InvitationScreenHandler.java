package com.example.eventmanager.view.invitation;

import com.example.eventmanager.Constain.ScreenPathConstain;
import com.example.eventmanager.controller.EventDetailController;
import com.example.eventmanager.controller.InvitationController;
import com.example.eventmanager.model.UserDTO;
import com.example.eventmanager.view.BaseScreenHandler;
import com.example.eventmanager.view.eventdetail.UserItem;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class InvitationScreenHandler extends BaseScreenHandler {

    @FXML // fx:id="containerUser"
    private VBox containerUser; // Value injected by FXMLLoader

    private List<UserDTO> userDTOList;

    @FXML
    void reloadUserList(MouseEvent event) {
        reloadUserList();
        displayUserList(getUserDTOList());
    }

    public InvitationScreenHandler(Stage stage, String screenPath) throws IOException {
        super(stage, screenPath);
    }

    @Override
    public void show() {
        reloadUserList();
        displayUserList(getUserDTOList());
        super.show();
    }

    public List<UserDTO> getUserDTOList() {
        return userDTOList;
    }

    public void setUserDTOList(List<UserDTO> userDTOList) {
        this.userDTOList = userDTOList;
    }

    public void reloadUserList() {
        InvitationController controller = (InvitationController) getBController();
        StringBuilder responseMess = new StringBuilder("");
        setUserDTOList(controller.getListUser(responseMess));
    }

    private void displayUserList(List<UserDTO> list) {
        this.containerUser.getChildren().clear();

        if (list != null) {
            for (UserDTO userDTO :
                    list) {
                try {
                    UserItem userItem = new UserItem(ScreenPathConstain.INVITATION_ITEM_SCREEN_PATH, userDTO);
                    this.containerUser.getChildren().add(userItem.getContent());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}