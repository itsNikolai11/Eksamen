package org.example;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import org.example.Components.CarComponent;
import org.example.Components.Kategorier;
import org.example.Components.ComponentRegister;
import org.example.Validation.ComponentValidation;

public class AdminController implements Initializable {

    @FXML
    private TableView<?> componentRegisterTable;
    @FXML
    private ChoiceBox<String> kategorySelect;
    @FXML
    private TableColumn<?, String> categoryColumn;

    @FXML
    private TableColumn<?, String> nameColumn;

    @FXML
    private TableColumn<?, Double> priceColumn;
    @FXML
    private TextField inputName;

    @FXML
    private TextField inputPrice;
    @FXML
    private Button btnRegister;
    @FXML
    private Label registerMsg;

    @FXML
    void registerComponent(ActionEvent event) {
        //TODO validering for input - sjekke om blank osv.
        try {
            if (ComponentValidation.validateComponent(inputName.getText(), inputPrice.getText(), kategorySelect.getValue())) {
                double pris = Double.parseDouble(inputPrice.getText());
                CarComponent component = new CarComponent(inputName.getText(), Double.parseDouble(inputPrice.getText()), kategorySelect.getValue());
                registerMsg.setText("Komponent registrert!");
                ComponentRegister.addComponent(component);
                clearTxtFields();
                //TODO lagre til fil her.
            }


        } catch (IllegalArgumentException e) {
            Dialogs.showErrorDialog(e.getMessage());
        }

    }

    @FXML
    void resetConfirmation(MouseEvent event) {
        registerMsg.setText("");
    }

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("user");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        kategorySelect.getItems().add("");
        for (Kategorier.Kategori cat : Kategorier.Kategori.values()) {
            kategorySelect.getItems().add(cat.name());
        }
        ComponentRegister.attachTableView(componentRegisterTable);
    }

    void clearTxtFields() {
        inputName.setText("");
        inputPrice.setText("");
        kategorySelect.setValue("");
    }

    @FXML
    void registerUser(ActionEvent event) throws IOException {
        App.setRoot("register");

    }

    public void saveFileClicked(ActionEvent event) {
    }

    public void openFileClicked(ActionEvent event) {

    }
}