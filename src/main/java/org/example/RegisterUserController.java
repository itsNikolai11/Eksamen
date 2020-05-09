package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.Exceptions.InvalidUserException;
import org.example.Exceptions.UserAlreadyExistsException;
import org.example.User.SuperUser;
import org.example.User.SuperUserManager;
import org.example.Validation.ValidateNewUser;

import java.io.IOException;

public class RegisterUserController {

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private TextField nameField;

    @FXML
    private Button register;

    @FXML
    private Label nameErrLabel;

    @FXML
    private Label passwordErrLabel;

    @FXML
    private Label confirmErrLabel;

    @FXML
    void registerUser(ActionEvent event) throws IOException {
        resetErrorLabels();
        try {
            ValidateNewUser.validateUser(nameField.getText());
            ValidateNewUser.validatePasswords(passwordField.getText(), confirmPasswordField.getText());
            SuperUserManager.registerUser(new SuperUser(nameField.getText(), passwordField.getText()));
            resetTextFields();
            App.setRoot("admin");

        } catch (UserAlreadyExistsException e) {
            nameErrLabel.setText("Navnet er allerede i bruk!");
        } catch (InvalidUserException e) {
            passwordErrLabel.setText(e.getMessage());
        } catch (IllegalArgumentException e) {
            nameErrLabel.setText(e.getMessage());
        } catch (IOException e) {
            Dialogs.showErrorDialog(e.getMessage());
        }



    }

    void resetErrorLabels() {
        nameErrLabel.setText("");
        passwordErrLabel.setText("");
        confirmErrLabel.setText("");
    }

    void resetTextFields() {
        nameField.setText("");
        passwordField.setText("");
        confirmPasswordField.setText("");
    }

}
