package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField brukernavn;

    @FXML
    private PasswordField passord;

    @FXML
    void avbrytInnlogging(ActionEvent event) throws IOException {
        App.setRoot("user");

    }

    @FXML
    void loggInn(ActionEvent event) throws IOException {
        App.setRoot("admin");
    }

}
