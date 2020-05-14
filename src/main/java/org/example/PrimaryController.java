package org.example;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.MenuButton;
import org.example.Components.ComponentRegister;

public class PrimaryController implements Initializable {
    @FXML
    private ChoiceBox<String> drivstoffMeny;

    @FXML
    private ChoiceBox<String> motorMeny;

    @FXML
    private ChoiceBox<String> fargeMeny;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //TODO loope gjennom elementer i kategorien Drivstoff og legge de til.
        // Forsikrer om at elementet man velger faktisk eksisterer. Oppdatere hver gang lista endres av superbruker
        drivstoffMeny.getItems().addAll("", "Bensin", "Diesel", "Elektrisitet", "Gass");
        motorMeny.getItems().addAll("", "1.9L", "2.0L", "3.0L");
        fargeMeny.getItems().addAll("", "Grønn", "Svart", "Sølv", "Grå", "Blå", "Rød");
    }
    void getComponents(){
        ComponentRegister.getCarComponents();
        //TODO legge til komponenter i menyen som matcher kategorien til komponenten
    }

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("login");
    }
}
