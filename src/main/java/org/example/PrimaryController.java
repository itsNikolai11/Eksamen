package org.example;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.MenuButton;
import org.example.Components.CarComponent;
import org.example.Components.Categories;
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
        //TODO loope gjennom elementer i kategorien Drivstoff og legge de til. Forsikrer om at elementet man velger faktisk eksisterer. Oppdatere hver gang lista endres av superbruker
        //drivstoffMeny.getItems().addAll("");
        getComponents();
        motorMeny.getItems().addAll("", "1.9L", "2.0L", "3.0L");
        fargeMeny.getItems().addAll("", "Grønn", "Svart", "Sølv", "Grå", "Blå", "Rød");

    }

    void getComponents() {
        ComponentRegister.getCarComponents();
        for (CarComponent comp : ComponentRegister.getCarComponents()) {
            if (comp.getKategori().equals(Categories.Kategori.Drivstoff.name())) {
                drivstoffMeny.getItems().add(comp.getNavn());
            }
        }
        drivstoffMeny.getItems().add("Test");
        //TODO legge til komponenter i menyen som matcher kategorien til komponenten
    }

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("login");
    }

}
