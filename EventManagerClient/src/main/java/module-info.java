module com.example.eventmanager {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.eventmanager to javafx.fxml;
    exports com.example.eventmanager;

    opens com.example.eventmanager.view.splash to javafx.fxml;
    exports com.example.eventmanager.view.splash;

    opens com.example.eventmanager.view.login to javafx.fxml;
    exports com.example.eventmanager.view.login;

    opens com.example.eventmanager.view.signup to javafx.fxml;
    exports com.example.eventmanager.view.signup;

    opens com.example.eventmanager.view.home to javafx.fxml;
    exports com.example.eventmanager.view.home;

    opens com.example.eventmanager.view.eventdetail to javafx.fxml;
    exports com.example.eventmanager.view.eventdetail;

    opens com.example.eventmanager.view.createevent to javafx.fxml;
    exports com.example.eventmanager.view.createevent;

    opens com.example.eventmanager.view.announcement to javafx.fxml;
    exports com.example.eventmanager.view.announcement;

    opens com.example.eventmanager.view.invitation to javafx.fxml;
    exports com.example.eventmanager.view.invitation;

    opens com.example.eventmanager.view.acceptant to javafx.fxml;
    exports com.example.eventmanager.view.acceptant;

}