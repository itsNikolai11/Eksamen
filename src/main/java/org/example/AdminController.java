package org.example;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.example.Components.CarComponent;
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
    private void switchToPrimary() throws IOException {
        App.setRoot("user");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        kategorySelect.getItems().addAll("", "Farge", "Motor", "Ekstrautstyr");
        ComponentRegister.attachTableView(componentRegisterTable);
    }

    void clearTxtFields() {
        inputName.setText("");
        inputPrice.setText("");
        kategorySelect.setValue("");
    }

    public void openFileClicked(ActionEvent actionEvent) {
        FileHandler.openFile();
    }

    public void saveFileClicked(ActionEvent actionEvent) {
        FileHandler.saveFile();
    }

    /*
    @FXML
    private void menuFileOpenClick() {

    }


    @FXML
    public void menuFileSaveClick(){FileHandler.saveFile();
    }
     */

}