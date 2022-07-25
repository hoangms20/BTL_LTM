package com.example.eventmanager.view.acceptant;

import com.example.eventmanager.Constain.ScreenPathConstain;
import com.example.eventmanager.controller.AcceptantController;
import com.example.eventmanager.model.EventDTO;
import com.example.eventmanager.model.RequestedEventDTO;
import com.example.eventmanager.model.UserDTO;
import com.example.eventmanager.view.BaseScreenHandler;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class AcceptantScreenHandler extends BaseScreenHandler {
    @FXML // fx:id="containerUser"
    private VBox containerUser; // Value injected by FXMLLoader

    private EventDTO eventDTO;

    private List<RequestedEventDTO> requestedEventDTOList;

    public AcceptantScreenHandler(Stage stage, String screenPath, EventDTO eventDTO) throws IOException {
        super(stage, screenPath);
        this.eventDTO = eventDTO;
    }

    @Override
    public void show() {
        reloadUserList();
        displayUserList(getRequestedEventDTOList());
        super.show();
    }

    @FXML
    void reload(MouseEvent event) {
        updateUserList();
    }

    public void updateUserList() {
        reloadUserList();
        displayUserList(getRequestedEventDTOList());
    }

    public List<RequestedEventDTO> getRequestedEventDTOList() {
        return requestedEventDTOList;
    }

    public void setRequestedEventDTOList(List<RequestedEventDTO> requestedEventDTOList) {
        this.requestedEventDTOList = requestedEventDTOList;
    }

    public void reloadUserList() {
        AcceptantController controller = (AcceptantController) getBController();
        StringBuilder responseMess = new StringBuilder("");
        List<RequestedEventDTO> requestedEventDTOList = controller.getListRequestedEvent(responseMess, eventDTO.getId());

        setRequestedEventDTOList(requestedEventDTOList);
    }

    private void displayUserList(List<RequestedEventDTO> list) {
        this.containerUser.getChildren().clear();

        if (list != null) {
            int i = 0;
            for (RequestedEventDTO event :
                    list) {
                try {
                    AcceptantItem item = new AcceptantItem(ScreenPathConstain.ACCEPTANT_ITEM_SCREEN_PATH, new UserDTO(event.getSender()), this.eventDTO, getBController());
                    item.setPrevScreen(this);
                    if (i % 2 == 0){
                        item.getFrame().setStyle("-fx-background-color: #FEFBE7");
                    }else {
                        item.getFrame().setStyle("-fx-background-color: #C4DDFF");
                    }
                    this.containerUser.getChildren().add(item.getContent());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                i++;
            }
        }
    }
}
