package org.example;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.example.Components.CarComponent;
import org.example.Components.Categories;
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
        for(Categories.Kategori cat : Categories.Kategori.values()){
            kategorySelect.getItems().add(cat.name());
        }
        //kategorySelect.getItems().addAll("", Categories.Kategori.Drivstoff.toString(), "Farge", "Motorstørrelse", "Ekstrautstyr");
        ComponentRegister.attachTableView(componentRegisterTable);
    }

    void clearTxtFields() {
        inputName.setText("");
        inputPrice.setText("");
        kategorySelect.setValue("");
    }
}