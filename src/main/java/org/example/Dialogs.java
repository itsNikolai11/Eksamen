package org.example;

import javafx.scene.control.Alert;
import javafx.stage.Modality;

public class Dialogs {
    public static void showErrorDialog(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("En feil har oppstått!");
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.show();
    }

    public static Alert showSaveDialog() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Lagrer data..");
        alert.setHeaderText(null);
        alert.setContentText("Lagrer data til fil. Vennligst vent.");
        alert.initModality(Modality.APPLICATION_MODAL);
        return alert;
        //TODO kun når lagringstråden har fullført.
    }
}
