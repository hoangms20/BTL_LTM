package com.example.eventmanager.view.login;

import com.example.eventmanager.controller.HomeController;
import com.example.eventmanager.controller.LoginController;
import com.example.eventmanager.controller.SignUpController;
import com.example.eventmanager.Constain.ScreenPathConstain;
import com.example.eventmanager.model.UserDTO;
import com.example.eventmanager.view.BaseScreenHandler;
import com.example.eventmanager.view.home.HomeScreenHandler;
import com.example.eventmanager.view.signup.SignUpScreenHandler;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/** Controls the login screen */
public class LoginScreenHandler extends BaseScreenHandler implements Initializable {
    private static String userNameLoggedIn;

    @FXML // fx:id="logo"
    private ImageView logo; // Value injected by FXMLLoader

    @FXML // fx:id="lockImage"
    private ImageView lockImage; // Value injected by FXMLLoader

    @FXML // fx:id="username"
    private TextField username; // Value injected by FXMLLoader

    @FXML // fx:id="password"
    private PasswordField password; // Value injected by FXMLLoader

    @FXML // fx:id="loginButton"
    private Button loginButton; // Value injected by FXMLLoader

    @FXML // fx:id="signUpButton"
    private Button signUpButton; // Value injected by FXMLLoader

    public LoginScreenHandler(Stage stage, String screenPath) throws IOException {
        super(stage, screenPath);
    }

    // This method is called by the FXMLLoader when initialization is complete
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.setImage(logo, "images/logo.png");
        super.setImage(lockImage, "images/lock.png");

    }

    @FXML
    public void userLogin(ActionEvent event){
        if (!invalidFields())
            return;

        LoginController controller = (LoginController) getBController();
        StringBuilder responseMess = new StringBuilder("");
        UserDTO user = new UserDTO(username.getText(), password.getText());

        int ret = controller.login(user, responseMess);

        if (ret == 0){
            try {
                setUserName(user.getUsername());
                username.clear();
                password.clear();
                HomeController homeController = new HomeController();
                HomeScreenHandler screen = new HomeScreenHandler(this.stage, ScreenPathConstain.HOME_SCREEN_PATH);
                screen.setBController(homeController);
                screen.setPreviousScreen(this);
                screen.show();
            }catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            announceWarning(responseMess.toString(),"Log in");
        }
    }

    private boolean invalidFields() {
        if (username.getText() == null || username.getText().equals("")){
            announceWarning("Please Enter Your User Name!", "Warning");
            return false;
        }

        if (password.getText() == null || password.getText().equals("")){
            announceWarning("Please Enter Your Password!", "Warning");
            return false;
        }

        LoginController controller = (LoginController) getBController();

        if (!controller.checkNotAllowedCharacter(username.getText()) || !controller.checkNotAllowedCharacter(password.getText()) ){
            announceWarning("User name and password can not include Forbidden Character!", "Warning");
            return false;
        }

        return true;
    }

    @FXML
    public void signUp(ActionEvent event) throws IOException {
        SignUpController signUpController = new SignUpController();
        SignUpScreenHandler signUpScreenHandler = new SignUpScreenHandler(this.stage, ScreenPathConstain.SIGN_UP_SCREEN_PATH);
        signUpScreenHandler.setScreenTitle("Sign Up Screen");
        signUpScreenHandler.setBController(signUpController);
        signUpScreenHandler.setPreviousScreen(this);
        signUpScreenHandler.show();
    }

    public static String getUserNameLoggedIn() {
        return userNameLoggedIn;
    }

    private void setUserName(String name) {
        userNameLoggedIn = name;
    }

}
