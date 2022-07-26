package com.example.eventmanager.view.invitation;

import com.example.eventmanager.Constain.ScreenPathConstain;
import com.example.eventmanager.controller.InvitationController;
import com.example.eventmanager.model.EventDTO;
import com.example.eventmanager.model.UserDTO;
import com.example.eventmanager.view.BaseScreenHandler;
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

    private EventDTO eventDTO;

    @FXML
    void reload(MouseEvent event) {
        reloadUserList();
        displayUserList(getUserDTOList());
    }

    public InvitationScreenHandler(Stage stage, String screenPath, EventDTO eventDTO) throws IOException {
        super(stage, screenPath);
        this.eventDTO = eventDTO;
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
            int i = 0;
            for (UserDTO userDTO :
                    list) {
                try {
                    InvitationItem item = new InvitationItem(ScreenPathConstain.INVITATION_ITEM_SCREEN_PATH, userDTO, this.eventDTO, getBController());
                    if (i % 2 == 0){
                        item.getFrame().setStyle("-fx-background-color: #FEFBE7");
                    }else {
                        item.getFrame().setStyle("-fx-background-color: #C4DDFF");
                    }
                    item.setPrevScreen(this);
                    this.containerUser.getChildren().add(item.getContent());
                    i++;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
