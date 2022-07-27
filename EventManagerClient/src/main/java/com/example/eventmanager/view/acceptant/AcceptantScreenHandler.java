package com.example.eventmanager.view.acceptant;

import com.example.eventmanager.constant.ScreenPathConstain;
import com.example.eventmanager.controller.AcceptantController;
import com.example.eventmanager.model.EventDTO;
import com.example.eventmanager.model.EventRequestDTO;
import com.example.eventmanager.model.UserDTO;
import com.example.eventmanager.view.BaseScreenHandler;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

/**
 * This class is Acceptant screen
 *
 * @author hoangnguyenthe20183925
 */
public class AcceptantScreenHandler extends BaseScreenHandler {
    @FXML // fx:id="containerUser"
    private VBox containerUser; // Value injected by FXMLLoader

    private EventDTO eventDTO;

    private List<EventRequestDTO> eventRequestDTOList;

    public AcceptantScreenHandler(Stage stage, String screenPath, EventDTO eventDTO) throws IOException {
        super(stage, screenPath);
        this.eventDTO = eventDTO;
    }

    /**
     * This function is to show screen
     *
     */
    @Override
    public void show() {
        reloadUserList();
        displayUserList(getRequestedEventDTOList());
        super.show();
    }


    /**
     * This function is to reload data
     *
     */
    @FXML
    void reload(MouseEvent event) {
        updateUserList();
    }

    public void updateUserList() {
        reloadUserList();
        displayUserList(getRequestedEventDTOList());
    }

    public List<EventRequestDTO> getRequestedEventDTOList() {
        return eventRequestDTOList;
    }

    public void setRequestedEventDTOList(List<EventRequestDTO> eventRequestDTOList) {
        this.eventRequestDTOList = eventRequestDTOList;
    }

    public void reloadUserList() {
        AcceptantController controller = (AcceptantController) getBController();
        StringBuilder responseMess = new StringBuilder("");
        List<EventRequestDTO> eventRequestDTOList = controller.getRequestedEventList(responseMess, eventDTO.getId());

        setRequestedEventDTOList(eventRequestDTOList);
    }

    private void displayUserList(List<EventRequestDTO> list) {
        this.containerUser.getChildren().clear();

        if (list != null) {
            int i = 0;
            for (EventRequestDTO event :
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
