package org.example;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.stage.Stage;
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
        drivstoffMeny.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> calculatePrice(drivstoffMeny, oldValue));
        modellMeny.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> calculatePrice(modellMeny, oldValue));
        motorMeny.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> calculatePrice(motorMeny, oldValue));
        fargeMeny.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> calculatePrice(fargeMeny, oldValue));
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

    void calculatePrice(ChoiceBox<String> box, String lastValue) {
        if (box.getValue().isEmpty() || box.getValue().isBlank()) {
            CarComponent component = ComponentRegister.getComponent(lastValue);
            if (component == null) {
                return;
            }
            try {
                Double d = Double.parseDouble(totalPrisLbl.getText());
                d -= component.getPris();
                totalPrisLbl.setText(d + "");
            } catch (NumberFormatException e) {
                Dialogs.showErrorDialog("Kunne ikke beregne pris!");
            }
            return;
        }
        CarComponent component = ComponentRegister.getComponent(box.getValue());
        if (component == null) {
            Dialogs.showErrorDialog("Fant ikke komponenten " + box.getValue());
            return;
        }
        try {
            Double d = Double.parseDouble(totalPrisLbl.getText());
            d += component.getPris();
            totalPrisLbl.setText(d + "");
        } catch (NumberFormatException e) {
            Dialogs.showErrorDialog("Kunne ikke beregne pris!");
        }
    }

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("login");
    }

    @FXML
    void saveConfiguration(ActionEvent event) {
        //TODO Lagre konfigurasjonen på et forhåndsdefinert sted som binær fil.
        //TODO henter value fra alle choice-boksene og lagrer disse.
    }
}
