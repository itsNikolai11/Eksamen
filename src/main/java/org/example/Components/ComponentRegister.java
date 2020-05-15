package org.example.Components;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import org.example.Dialogs;
import org.example.filbehandling.ComponentFileOpener;
import org.example.filbehandling.ComponentFileSaver;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ComponentRegister {
    private static ObservableList<CarComponent> carComponents;

    //TODO lagre denne listen til fil hver gang en ny komponent legges til eller endres i admin-vindu
    private ComponentRegister() {

        carComponents = FXCollections.observableArrayList();
        load();

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
        save();

    }

    public static ObservableList<CarComponent> getCarComponents() {
        if (carComponents == null) {
            new ComponentRegister();
        }
        return carComponents;
    }

    public static CarComponent getComponent(String component) {
        for (int i = 0; i < carComponents.size(); i++) {
            if (carComponents.get(i).getNavn().equals(component)) {
                return carComponents.get(i);
            }
        }
        return null;
    }

    public static void save() {
        ComponentFileSaver saver = new ComponentFileSaver();
        try {
            saver.save(new ArrayList<>(carComponents));
        } catch (IOException e) {
            Dialogs.showErrorDialog("Feil under lagring: \n" + e.getMessage());
        }
    }

    public static void load() {
        ComponentFileOpener opener = new ComponentFileOpener();
        try {
            List<CarComponent> components = (List<CarComponent>) opener.load();
            for (CarComponent comp : components) {
                addComponent(comp);
            }
        } catch (IOException | ClassNotFoundException e) {
            Dialogs.showErrorDialog("Kunne ikke laste inn fil!");
        }
    }
}
