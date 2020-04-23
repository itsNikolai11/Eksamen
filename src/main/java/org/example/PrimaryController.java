package org.example;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.MenuButton;

public class PrimaryController {
    @FXML
    private ChoiceBox<?> drivstoffMeny;

    @FXML
    private ChoiceBox<?> motorMeny;

    @FXML
    private ChoiceBox<?> fargeMeny;

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("login");
    }
}
