package com.example.eventmanager.view.signup;

import com.example.eventmanager.controller.SignUpController;
import com.example.eventmanager.model.UserDTO;
import com.example.eventmanager.view.BaseScreenHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controls the login screen
 */
public class SignUpScreenHandler extends BaseScreenHandler implements Initializable {
    @FXML // fx:id="logo"
    private ImageView logo; // Value injected by FXMLLoader

    @FXML // fx:id="username"
    private TextField username; // Value injected by FXMLLoader

    @FXML // fx:id="password"
    private PasswordField password; // Value injected by FXMLLoader

    @FXML // fx:id="repassword"
    private PasswordField repassword; // Value injected by FXMLLoader

    @FXML // fx:id="submitButton"
    private Button submitButton; // Value injected by FXMLLoader

    @FXML // fx:id="cancelButton"
    private Button cancelButton; // Value injected by FXMLLoader

    public SignUpScreenHandler(Stage stage, String screenPath) throws IOException {
        super(stage, screenPath);
    }


    // This method is called by the FXMLLoader when initialization is complete
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.setImage(logo, "images/logo.png");

    }


    @FXML
    public void submit(ActionEvent event) {
        if (!invalidFields())
            return;

        SignUpController controller = (SignUpController) getBController();
        StringBuilder responseMess = new StringBuilder("");
        UserDTO user = new UserDTO(username.getText(), password.getText());

        int ret = controller.signUp(user, responseMess);

        if (ret == 0) {
            username.clear();
            password.clear();
            announceInfo("Sign Up Successfully!\nPlease Login by User Which you have created!", "Sign up");
            getPreviousScreen().show();
        } else {
            announceWarning("Sign up", responseMess.toString());
        }
    }

    @FXML
    public void cancel(ActionEvent event) {
        getPreviousScreen().show();
    }

    private boolean invalidFields() {
        if (username.getText() == null || username.getText().equals("")) {
            announceWarning("Please Enter User Name!", "Warning");
            return false;
        }

        if (password.getText() == null || password.getText().equals("")) {
            announceWarning("Please Enter Password!", "Warning");
            return false;
        }

        if (repassword.getText() == null || repassword.getText().equals("")) {
            announceWarning("Please Enter Repassword!", "Warning");
            return false;
        }


        if (!password.getText().equals(repassword.getText())) {
            announceWarning("Password and Repassword do not match!", "Warning");
            return false;
        }

        SignUpController controller = (SignUpController) getBController();

        if (!controller.checkNotAllowedCharacter(username.getText())){
            announceWarning("Fields can not include Forbidden Character!", "Warning");
            return false;
        }

        if (!controller.checkNotAllowedCharacter(password.getText())){
            announceWarning("Fields can not include Forbidden Character!", "Warning");
            return false;
        }

        return true;
    }

}
