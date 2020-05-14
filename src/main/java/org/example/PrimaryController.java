package org.example;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.TilePane;
import org.example.Components.CarComponent;
import org.example.Components.Kategorier;
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
    private TilePane ekstrautstyrContainer;
    @FXML
    private Label totalPrisLbl;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        drivstoffMeny.getItems().addAll("");
        modellMeny.getItems().add("");
        motorMeny.getItems().addAll("");
        fargeMeny.getItems().addAll("");
        drivstoffMeny.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> calculatePrice(newValue, oldValue));
        modellMeny.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> calculatePrice(newValue, oldValue));
        motorMeny.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> calculatePrice(newValue, oldValue));
        fargeMeny.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> calculatePrice(newValue, oldValue));
        getComponents();
    }

    void getComponents() {
        ComponentRegister.getCarComponents();
        for (CarComponent comp : ComponentRegister.getCarComponents()) {
            if (comp.getKategori().equals(Kategorier.Kategori.Drivstoff.name())) {
                drivstoffMeny.getItems().add(comp.getNavn());
            }
            if (comp.getKategori().equals(Kategorier.Kategori.Bilmerke.name())) {
                modellMeny.getItems().add(comp.getNavn());
            }
            if (comp.getKategori().equals(Kategorier.Kategori.Ekstrautstyr.name())) {
                createCheckbox(comp);
            }
            if (comp.getKategori().equals(Kategorier.Kategori.Farge.name())) {
                fargeMeny.getItems().add(comp.getNavn());
            }
            if (comp.getKategori().equals(Kategorier.Kategori.Motorstørrelse.name())) {
                motorMeny.getItems().add(comp.getNavn());
            }
        }

    }

    void createCheckbox(CarComponent component) {
        CheckBox box = new CheckBox(component.getNavn());
        box.selectedProperty().addListener((observableValue, oldValue, newValue) -> {
            if (box.isSelected()) {
                try {
                    double d = Double.parseDouble(totalPrisLbl.getText());
                    d += ComponentRegister.getComponent(box.getText()).getPris();
                    totalPrisLbl.setText(d + "");
                } catch (NumberFormatException e) {
                    Dialogs.showErrorDialog("Kunne ikke beregne pris! Ugyldig tallverdi.");
                }
            } else {
                try {
                    double d = Double.parseDouble(totalPrisLbl.getText());
                    d -= ComponentRegister.getComponent(box.getText()).getPris();
                    totalPrisLbl.setText(d + "");
                } catch (NumberFormatException e) {
                    Dialogs.showErrorDialog("Kunne ikke beregne pris! Ugyldig tallverdi.");
                }
            }
        });
        ekstrautstyrContainer.getChildren().add(box);
    }

    void calculatePrice(String newValue, String lastValue) {
        CarComponent component;
        if (newValue.isEmpty() || newValue.isBlank()) {
            component = ComponentRegister.getComponent(lastValue);
            if (component == null) {
                return;
            }
            try {
                double d = Double.parseDouble(totalPrisLbl.getText());
                d -= component.getPris();
                totalPrisLbl.setText(d + "");
            } catch (NumberFormatException e) {
                Dialogs.showErrorDialog("Kunne ikke beregne pris!");
            }
            return;
        }
        component = ComponentRegister.getComponent(newValue);
        if (component == null) {
            Dialogs.showErrorDialog("Fant ikke komponenten " + newValue);
            return;
        }
        try {
            double d = Double.parseDouble(totalPrisLbl.getText());
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

    public void saveFileClicked(ActionEvent event) {
    }

    public void openFileClicked(ActionEvent event) {
    }
}
