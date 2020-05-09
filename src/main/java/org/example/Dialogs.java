package org.example;

import javafx.scene.control.Alert;

public class Dialogs {
    public static void showErrorDialog(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("En feil har oppstått!");
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.show();
    }
    public static Alert showSaveDialog(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Lagrer data..");
        alert.setHeaderText(null);
        alert.setContentText("Lagrer data til fil. Vennligst vent.");
        return alert;
        //TODO kun når lagringstråden har fullført.
    }
}
