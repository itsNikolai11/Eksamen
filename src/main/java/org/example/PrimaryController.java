package org.example;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.TilePane;
import org.example.Components.CarComponent;
import org.example.Components.Kategorier;
import org.example.Components.ComponentRegister;
import org.example.filbehandling.ConfigFileOpener;
import org.example.filbehandling.ConfigFileSaver;

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

    public static ArrayList<String> configuration = new ArrayList<>();
    private ChangeListener priceListener = new ChangeListener<String>() {
        @Override
        public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
            calculatePrice(newValue, oldValue);
        }
    };
    private ChangeListener configListener = (ChangeListener<String>) (observableValue, oldValue, newValue) -> {
        configuration.remove(oldValue);
        configuration.add(newValue);
    };

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        drivstoffMeny.getItems().addAll("");
        modellMeny.getItems().add("");
        motorMeny.getItems().addAll("");
        fargeMeny.getItems().addAll("");
        drivstoffMeny.getSelectionModel().selectedItemProperty().addListener(priceListener);
        modellMeny.getSelectionModel().selectedItemProperty().addListener(priceListener);
        motorMeny.getSelectionModel().selectedItemProperty().addListener(priceListener);
        fargeMeny.getSelectionModel().selectedItemProperty().addListener(priceListener);
        addListeners();
        getComponents();
    }

    void addListeners() {
        drivstoffMeny.getSelectionModel().selectedItemProperty().addListener(configListener);
        modellMeny.getSelectionModel().selectedItemProperty().addListener(configListener);
        motorMeny.getSelectionModel().selectedItemProperty().addListener(configListener);
        fargeMeny.getSelectionModel().selectedItemProperty().addListener(configListener);
    }

    void removeListeners() {
        drivstoffMeny.getSelectionModel().selectedItemProperty().removeListener(configListener);
        modellMeny.getSelectionModel().selectedItemProperty().removeListener(configListener);
        motorMeny.getSelectionModel().selectedItemProperty().removeListener(configListener);
        fargeMeny.getSelectionModel().selectedItemProperty().removeListener(configListener);
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

    @FXML
    void loadConfig() {
        ConfigFileOpener opener = new ConfigFileOpener();
        try {
            configuration = opener.load();
        } catch (IOException | ClassNotFoundException e) {
            Dialogs.showErrorDialog("En feil oppstod under innlasting av fil. \n" + e.getMessage());
        }
        removeListeners();
        verifyConfig();
        for (String s : configuration) {
            CarComponent comp = ComponentRegister.getComponent(s);
            if (comp.getKategori().equals(Kategorier.Kategori.Drivstoff.name())) {
                drivstoffMeny.setValue(comp.getNavn());
            }
            if (comp.getKategori().equals(Kategorier.Kategori.Bilmerke.name())) {
                modellMeny.setValue(comp.getNavn());
            }
            if (comp.getKategori().equals(Kategorier.Kategori.Ekstrautstyr.name())) {
                createCheckbox(comp);
                //TODO marker checkboxene med komponenten.

            }
            if (comp.getKategori().equals(Kategorier.Kategori.Farge.name())) {
                fargeMeny.setValue(comp.getNavn());
            }
            if (comp.getKategori().equals(Kategorier.Kategori.Motorstørrelse.name())) {
                motorMeny.setValue(comp.getNavn());
            }
        }
        addListeners();
    }

    void verifyConfig() {
        for (String s : configuration) {
            CarComponent comp = ComponentRegister.getComponent(s);
            //TODO dette fungerer ikke
            if (comp == null) {
                Dialogs.showErrorDialog("Konfigurasjonen inneholder ugyldige komponenter");
                configuration.remove(s);
                return;
            }
        }
    }

    void saveConfig() {
        ConfigFileSaver saver = new ConfigFileSaver();
        try {
            saver.save();
        } catch (IOException | InterruptedException e) {
            Dialogs.showErrorDialog("Kunne ikke lagre fil! \n" + e.getMessage());
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
        saveConfig();
    }
}
