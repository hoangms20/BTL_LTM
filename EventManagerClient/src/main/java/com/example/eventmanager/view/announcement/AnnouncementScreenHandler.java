package com.example.eventmanager.view.announcement;

import com.example.eventmanager.Constain.ScreenPathConstain;
import com.example.eventmanager.controller.AnnouncementController;
import com.example.eventmanager.model.EventRequestDTO;
import com.example.eventmanager.model.ReplyDTO;
import com.example.eventmanager.view.BaseScreenHandler;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

/**
 * This class is Announcement screen
 *
 * @author hoangnguyenthe20183925
 */
public class AnnouncementScreenHandler extends BaseScreenHandler {

    @FXML // fx:id="container"
    private VBox container; // Value injected by FXMLLoader

    private List<EventRequestDTO> invitedEventList;

    private List<EventRequestDTO> requestedEventList;

    private List<ReplyDTO> requestReplyList;

    private List<ReplyDTO> invitationReplyList;

    public AnnouncementScreenHandler(Stage stage, String screenPath) throws IOException {
        super(stage, screenPath);

    }

    public List<EventRequestDTO> getInvitedEventList() {
        return invitedEventList;
    }

    public void setInvitedEventList(List<EventRequestDTO> invitedEventList) {
        this.invitedEventList = invitedEventList;
    }

    public List<EventRequestDTO> getRequestedEventList() {
        return requestedEventList;
    }

    public void setRequestedEventList(List<EventRequestDTO> requestedEventList) {
        this.requestedEventList = requestedEventList;
    }

    public List<ReplyDTO> getRequestReplyList() {
        return requestReplyList;
    }

    public void setRequestReplyList(List<ReplyDTO> requestReplyList) {
        this.requestReplyList = requestReplyList;
    }

    public List<ReplyDTO> getInvitationReplyList() {
        return invitationReplyList;
    }

    public void setInvitationReplyList(List<ReplyDTO> invitationReplyList) {
        this.invitationReplyList = invitationReplyList;
    }

    @Override
    public void show() {
        updateAnnouncementList();
        displayAnnouncementList(getInvitedEventList(), getRequestedEventList(), getRequestReplyList(), getInvitationReplyList());

        super.show();
    }

    private String setAnnouncementForAInvitedEvent(EventRequestDTO event) {
        String s = event.getSender() + " has \"invited\" you to join a event: (" + event.getEventId() + ")";
        return s;
    }

    private String setAnnouncementForARequestedEvent(EventRequestDTO event) {
        String s = event.getSender() + " has \"requested\" to join a event of you: (" + event.getEventId() + ")";
        return s;
    }

    private String setAnnouncementForAInvitationReply(ReplyDTO replyDTO) {
        String re = "accepted";
        if (!replyDTO.getReply().equals("OK")) {
            re = "refused";
        }
        String s = replyDTO.getSender() + " has \"" + re + "\" to join your event: (" + replyDTO.getEventId() + ")";
        return s;
    }

    private String setAnnouncementForARequestReply(ReplyDTO replyDTO) {
        String re = "accepted";
        if (!replyDTO.getReply().equals("OK")) {
            re = "refused";
        }
        String s = replyDTO.getSender() + " has \"" + re + "\" your request to join a event: (" + replyDTO.getEventId() + ")";
        return s;
    }

    private void displayAnnouncementList(List<EventRequestDTO> invitedEventDTOList, List<EventRequestDTO> requestedEventList,
                                         List<ReplyDTO> requestReplyList, List<ReplyDTO> invitationReplyList) {
        this.container.getChildren().clear();
        int i = 0;

        if (invitedEventDTOList != null) {
            for (EventRequestDTO event :
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
            for (EventRequestDTO event :
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

        if (requestReplyList != null) {
            for (ReplyDTO replyDTO :
                    requestReplyList) {
                try {
                    AnnouncementItem item = new AnnouncementItem(ScreenPathConstain.ANNOUNCEMENT_ITEM_SCREEN_PATH, setAnnouncementForARequestReply(replyDTO));
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

        if (invitationReplyList != null) {
            for (ReplyDTO replyDTO :
                    invitationReplyList) {
                try {
                    AnnouncementItem item = new AnnouncementItem(ScreenPathConstain.ANNOUNCEMENT_ITEM_SCREEN_PATH, setAnnouncementForAInvitationReply(replyDTO));
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
        displayAnnouncementList(getInvitedEventList(), getRequestedEventList(), getRequestReplyList(), getInvitationReplyList());
    }

    private void updateAnnouncementList() {
        AnnouncementController controller = (AnnouncementController) getBController();
        StringBuilder responseMess = new StringBuilder("");
        setInvitedEventList(controller.getListInvitedEvent(responseMess));

        responseMess = new StringBuilder("");
        setRequestedEventList(controller.getListRequestedEvent(responseMess));

        responseMess = new StringBuilder("");
        setRequestReplyList(controller.getListRequestReply(responseMess));

        responseMess = new StringBuilder("");
        setInvitationReplyList(controller.getListInvitationReply(responseMess));
    }
}
