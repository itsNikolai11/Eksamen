package org.example;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
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
    @FXML
    private ChoiceBox<String> modellMeny;

    @FXML
    private Label totalPrisLbl;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //TODO loope gjennom elementer i kategorien Drivstoff og legge de til. Forsikrer om at elementet man velger faktisk eksisterer. Oppdatere hver gang lista endres av superbruker
        drivstoffMeny.getItems().addAll("");
        modellMeny.getItems().add("");
        motorMeny.getItems().addAll("");
        fargeMeny.getItems().addAll("");
        getComponents();

    }

    void getComponents() {
        ComponentRegister.getCarComponents();
        for (CarComponent comp : ComponentRegister.getCarComponents()) {
            if (comp.getKategori().equals(Categories.Kategori.Drivstoff.name())) {
                drivstoffMeny.getItems().add(comp.getNavn());
            }
            if (comp.getKategori().equals(Categories.Kategori.Bilmerke.name())) {
                modellMeny.getItems().add(comp.getNavn());
            }
            /*if (comp.getKategori().equals(Categories.Kategori.Ekstrautstyr.name())) {
                modellMeny.getItems().add(comp.getNavn());
            }*/
            if (comp.getKategori().equals(Categories.Kategori.Farge.name())) {
                fargeMeny.getItems().add(comp.getNavn());
            }
            if (comp.getKategori().equals(Categories.Kategori.Motorstørrelse.name())) {
                motorMeny.getItems().add(comp.getNavn());
            }
        }

    }

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("login");
    }

    @FXML
    void saveConfiguration(ActionEvent event) {

    }
}
