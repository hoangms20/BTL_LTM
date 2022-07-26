package com.example.eventmanager.view.announcement;

import com.example.eventmanager.view.FXMLScreenHandler;
import javafx.fxml.FXML;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.io.IOException;

/**
 * This class is item of Announcement screen
 *
 * @author hoangnguyenthe20183925
 */
public class AnnouncementItem extends FXMLScreenHandler {
    public static final Font font = new Font("Calibri Light", 18);

    @FXML // fx:id="detailAnnouncement"
    private TextFlow detailAnnouncement; // Value injected by FXMLLoader

    public AnnouncementItem(String screenPath) throws IOException {
        super(screenPath);
    }

    public AnnouncementItem(String screenPath, String announcement) throws IOException {
        super(screenPath);

        Text text = new Text(announcement);
        text.setFont(font);
        detailAnnouncement.getChildren().add(text);
    }

    public TextFlow getDetailAnnouncement() {
        return detailAnnouncement;
    }

}
