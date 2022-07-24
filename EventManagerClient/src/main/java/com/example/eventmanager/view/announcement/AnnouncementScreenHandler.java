package com.example.eventmanager.view.announcement;

import com.example.eventmanager.Constain.ScreenPathConstain;
import com.example.eventmanager.controller.AnnouncementController;
import com.example.eventmanager.model.EventDTO;
import com.example.eventmanager.model.RequestedEventDTO;
import com.example.eventmanager.view.BaseScreenHandler;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class AnnouncementScreenHandler extends BaseScreenHandler {



    @FXML // fx:id="container"
    private VBox container; // Value injected by FXMLLoader

    private List<EventDTO> invitedEventList;

    private List<RequestedEventDTO> requestedEventList;

    public AnnouncementScreenHandler(Stage stage, String screenPath) throws IOException {
        super(stage, screenPath);

    }

    @Override
    public void show() {
        updateAnnouncementList();
        displayAnnouncementList(getInvitedEventList(), getRequestedEventList());

        super.show();
    }

    private String setAnnouncementForAInvitedEvent(EventDTO event) {
        String s = event.getCreatedBy() + " has \"invited\" you to join a event: (" + event.getId() + ")" + event.getName();
        return s;
    }

    private String setAnnouncementForARequestedEvent(RequestedEventDTO event) {
        String s = event.getSender() + " has \"requested\" to join a event of you: (" + event.getId() + ")" + event.getName();
        return s;
    }

    private void displayAnnouncementList(List<EventDTO> invitedEventDTOList, List<RequestedEventDTO> requestedEventList) {
        this.container.getChildren().clear();
        int i = 0;

        if (invitedEventDTOList != null) {
            for (EventDTO event :
                    invitedEventDTOList) {
                try {
                    AnnouncementItem item = new AnnouncementItem(ScreenPathConstain.ANNOUNCEMENT_ITEM_SCREEN_PATH, setAnnouncementForAInvitedEvent(event));
                    if (i % 2 == 0) {
                        item.getDetailAnnouncement().setStyle("-fx-background-color: #A5C9CA");
                    } else {
                        item.getDetailAnnouncement().setStyle("-fx-background-color: #D4F6CC");
                    }

                    this.container.getChildren().add(item.getContent());

                    i++;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        if (requestedEventList != null) {
            for (RequestedEventDTO event :
                    requestedEventList) {
                try {
                    AnnouncementItem item = new AnnouncementItem(ScreenPathConstain.ANNOUNCEMENT_ITEM_SCREEN_PATH, setAnnouncementForARequestedEvent(event));
                    if (i % 2 == 0) {
                        item.getDetailAnnouncement().setStyle("-fx-background-color: #A5C9CA");
                    } else {
                        item.getDetailAnnouncement().setStyle("-fx-background-color: #D4F6CC");
                    }

                    this.container.getChildren().add(item.getContent());

                    i++;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @FXML
    void reloadAnnouncement(MouseEvent event) {
        updateAnnouncementList();
        displayAnnouncementList(getInvitedEventList(), getRequestedEventList());
    }

    private void updateAnnouncementList() {
        AnnouncementController controller = (AnnouncementController) getBController();
        StringBuilder responseMess = new StringBuilder("");
        setInvitedEventList(controller.getListInvitedEvent(responseMess));

        responseMess = new StringBuilder("");
        setRequestedEventList(controller.getListRequestedEvent(responseMess));
    }

    public List<EventDTO> getInvitedEventList() {
        return invitedEventList;
    }

    public void setInvitedEventList(List<EventDTO> invitedEventList) {
        this.invitedEventList = invitedEventList;
    }

    public List<RequestedEventDTO> getRequestedEventList() {
        return requestedEventList;
    }

    public void setRequestedEventList(List<RequestedEventDTO> requestedEventList) {
        this.requestedEventList = requestedEventList;
    }
}
