package org.example;

import javafx.scene.control.Alert;

public class Dialogs {
    public static void showErrorDialog(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        //alert.getDialogPane().getStylesheets().add("style.css");
        alert.setTitle("En feil har oppstått!");
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.show();

    }
}
