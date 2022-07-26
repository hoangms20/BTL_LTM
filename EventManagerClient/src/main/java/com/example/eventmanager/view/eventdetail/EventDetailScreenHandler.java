package com.example.eventmanager.view.eventdetail;

import com.example.eventmanager.Constain.ScreenPathConstain;
import com.example.eventmanager.controller.AcceptantController;
import com.example.eventmanager.controller.EventDetailController;
import com.example.eventmanager.controller.InvitationController;
import com.example.eventmanager.model.EventDTO;
import com.example.eventmanager.model.UserDTO;
import com.example.eventmanager.view.BaseScreenHandler;
import com.example.eventmanager.view.invitation.InvitationScreenHandler;
import com.example.eventmanager.view.acceptant.AcceptantScreenHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class EventDetailScreenHandler extends BaseScreenHandler implements Initializable {

    public static final Font font = new Font("Calibri Light", 18);
    public static final String OK_RELPY = "OK";
    public static final String DENY_RELPY = "DENY";

    @FXML // fx:id="logo"
    private ImageView logo; // Value injected by FXMLLoader

    @FXML // fx:id="logoutMenuItem"
    private MenuItem logoutMenuItem; // Value injected by FXMLLoader

    @FXML // fx:id="closeMenuItem"
    private MenuItem closeMenuItem; // Value injected by FXMLLoader

    @FXML // fx:id="changePasswordMenuItem"
    private MenuItem changePasswordMenuItem; // Value injected by FXMLLoader

    @FXML // fx:id="joinRequestButton"
    private Button joinRequestButton; // Value injected by FXMLLoader

    @FXML // fx:id="announcementLabel"
    private Label announcementLabel; // Value injected by FXMLLoader

    @FXML // fx:id="acceptInvitationBtn"
    private Button acceptInvitationBtn; // Value injected by FXMLLoader

    @FXML // fx:id="inviteButton"
    private Button inviteButton; // Value injected by FXMLLoader

    @FXML // fx:id="acceptButton"
    private Button acceptButton; // Value injected by FXMLLoader

    @FXML // fx:id="scrollPane"
    private ScrollPane scrollPane; // Value injected by FXMLLoader

    @FXML // fx:id="eventId"
    private Label eventId; // Value injected by FXMLLoader

    @FXML // fx:id="eventName"
    private Label eventName; // Value injected by FXMLLoader

    @FXML // fx:id="createdBy"
    private Label createdBy; // Value injected by FXMLLoader

    @FXML // fx:id="time"
    private Label time; // Value injected by FXMLLoader

    @FXML // fx:id="place"
    private Label place; // Value injected by FXMLLoader

    @FXML // fx:id="description"
    private TextFlow description; // Value injected by FXMLLoader

    @FXML // fx:id="containerUser"
    private VBox containerUser; // Value injected by FXMLLoader

    private EventDTO eventDTO;
    private List<UserDTO> userDTOList;
    private boolean isInvited;

    public EventDetailScreenHandler(Stage stage, String screenPath) throws IOException {
        super(stage, screenPath);
    }

    public EventDetailScreenHandler(Stage stage, String screenPath, EventDTO eventDTO) throws IOException {
        super(stage, screenPath);
        setEventDTO(eventDTO);
    }

    public List<UserDTO> getUserDTOList() {
        return userDTOList;
    }

    public void setUserDTOList(List<UserDTO> userDTOList) {
        this.userDTOList = userDTOList;
    }

    // This method is called by the FXMLLoader when initialization is complete
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.setImage(logo, "images/logo.png");

        logoutMenuItem.setDisable(true);
        changePasswordMenuItem.setDisable(true);
    }

    @Override
    public void show() {
        EventDetailController controller = (EventDetailController) getBController();
        if (eventDTO.getCreatedBy().equals(controller.getUserName())){
            this.joinRequestButton.setDisable(true);
            this.joinRequestButton.setVisible(false);
            this.inviteButton.setDisable(false);
            this.acceptButton.setDisable(false);
            this.announcementLabel.setVisible(true);
            this.acceptInvitationBtn.setDisable(true);
            this.acceptInvitationBtn.setVisible(false);
        }
        else {
            this.joinRequestButton.setDisable(false);
            this.joinRequestButton.setVisible(true);
            this.inviteButton.setDisable(true);
            this.acceptButton.setDisable(true);
            this.announcementLabel.setVisible(false);
        }

        updateJoinedUserList();
        displayUserAttendList(getUserDTOList());

        super.show();
    }

    public void setEventDTO(EventDTO eventDTO) {
        this.eventDTO = eventDTO;

        eventId.setText(eventDTO.getId());
        eventName.setText(eventDTO.getName());
        time.setText(eventDTO.getTime());
        place.setText(eventDTO.getPlace());
        createdBy.setText(eventDTO.getCreatedBy());

        Text text = new Text(eventDTO.getDescription());
        text.setFont(font);
        description.getChildren().add(text);
    }

    public void updateJoinedUserList() {
        EventDetailController controller = (EventDetailController) getBController();
        StringBuilder responseMess = new StringBuilder("");
        setUserDTOList(controller.getListUserAttend(eventDTO, responseMess));
    }

    private void displayUserAttendList(List<UserDTO> list) {
        this.containerUser.getChildren().clear();

        if (list != null) {
            for (UserDTO userDTO :
                    list) {
                try {
                    UserItem userItem = new UserItem(ScreenPathConstain.USER_ITEM_SCREEN_PATH, userDTO);
                    this.containerUser.getChildren().add(userItem.getContent());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @FXML
    void invite(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        InvitationController controller = new InvitationController();
        InvitationScreenHandler invitationScreenHandler = new InvitationScreenHandler(stage, ScreenPathConstain.INVITATION_SCREEN_PATH, this.eventDTO);
        invitationScreenHandler.setScreenTitle("Invitation screen");
        invitationScreenHandler.setBController(controller);
        invitationScreenHandler.show();

    }

    @FXML
    void acceptRequest(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        AcceptantController controller = new AcceptantController();
        AcceptantScreenHandler acceptantScreenHandler = new AcceptantScreenHandler(stage, ScreenPathConstain.ACCEPTANT_SCREEN_PATH, this.eventDTO);
        acceptantScreenHandler.setScreenTitle("Acceptant screen");
        acceptantScreenHandler.setBController(controller);
        acceptantScreenHandler.show();

    }

    @FXML
    void sendRequestToJoin(ActionEvent event) {
        if (announceConfirm("Are you sure?", "Comfirm")) {
            EventDetailController controller = (EventDetailController) getBController();
            UserDTO userDTO = new UserDTO();
            userDTO.setUsername(controller.getUserName());

            StringBuilder responseMess = new StringBuilder("");

            if (controller.requestToJoin(eventDTO, userDTO, responseMess) != 0) {
                announceError(responseMess.toString(), "Error");
            } else {
                announceInfo("Successfully!", "OK");
            }
        }

    }

    @FXML
    void reloadJoinedUserList(MouseEvent event) {
        updateJoinedUserList();
        displayUserAttendList(getUserDTOList());
    }

    @FXML
    void acceptInvitation(ActionEvent event) {
        EventDetailController controller = (EventDetailController) getBController();
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(controller.getUserName());
        StringBuilder responseMess = new StringBuilder("");

        if (announceConfirm("Do you agree to join this event?", "Comfirm")) {
            if (controller.replyInvitation(eventDTO, userDTO, OK_RELPY, responseMess) != 0) {
                announceError(responseMess.toString(), "Error");
            } else {
                announceInfo("Successfully!", "OK");
            }
        }else {
            if (controller.replyInvitation(eventDTO, userDTO, DENY_RELPY, responseMess) != 0) {
                announceError(responseMess.toString(), "Error");
            } else {
                announceInfo("Successfully!", "OK");
            }
        }

    }
}
