package org.example;

import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.example.Exceptions.InvalidLoginException;
import org.example.User.SuperUser;
import org.example.User.SuperUserManager;
import org.example.User.VerifyUser;

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
    void loggInn(ActionEvent event) throws IOException, InvalidLoginException {
        checkCredentials();
    }

    @FXML
    void enterPressed(KeyEvent event) throws IOException {
        if (event.getCode() == KeyCode.ENTER) {
            checkCredentials();
        }

    }

    void checkCredentials() throws IOException {
        SuperUser su = SuperUserManager.getSuperUser(brukernavn.getText());
        try {
            VerifyUser.verifyUser(brukernavn.getText(), passord.getText());
        } catch (InvalidLoginException e) {
            Dialogs.showErrorDialog(e.getMessage());
            return;
        }
        if (passord.getText().equals(su.getPassord())) {
            App.setRoot("admin");
        } else {
            Dialogs.showErrorDialog("Feil passord");
        }
    }


}
