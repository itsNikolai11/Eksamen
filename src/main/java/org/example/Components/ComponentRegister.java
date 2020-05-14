package org.example.Components;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import org.example.Dialogs;
import org.example.filbehandling.ComponentFileOpener;

import java.io.IOException;

public class ComponentRegister {
    private static ObservableList<CarComponent> carComponents;

    //TODO lagre denne listen til fil hver gang en ny komponent legges til eller endres i admin-vindu
    private ComponentRegister() {
        carComponents = FXCollections.observableArrayList();
        ComponentFileOpener opener = new ComponentFileOpener();

        /*try{
            carComponents = FXCollections.observableArrayList();
            opener.open();
        }  catch (IOException exc){
            Dialogs.showErrorDialog(exc.getMessage());
        }
*/
        //TODO last inn lagrede komponenter
    }

    public static void attachTableView(TableView tv) {
        if (carComponents == null) {
            new ComponentRegister();
        }
        tv.setItems(carComponents);
    }

    public static void attachFilteredTableView(TableView tv, ObservableList filteredList) {
        if (carComponents == null) {
            new ComponentRegister();
        }
        tv.setItems(filteredList);
    }

    public static void addComponent(CarComponent component) {
        if (carComponents == null) {
            new ComponentRegister();
        }

        carComponents.add(component);
    }

    public static ObservableList<CarComponent> getCarComponents() {
        if (carComponents == null) {
            new ComponentRegister();
        }
        return carComponents;
    }


}
