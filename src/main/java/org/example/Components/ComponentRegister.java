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
import java.util.ArrayList;
import java.util.List;

public class ComponentRegister {
    private static ObservableList<CarComponent> carComponents;

    //TODO lagre denne listen til fil hver gang en ny komponent legges til eller endres i admin-vindu
    private ComponentRegister() {
        ComponentFileOpener opener = new ComponentFileOpener();
        try{
            carComponents = (ObservableList<CarComponent>) opener.load();
        }  catch (ClassNotFoundException | IOException e){
            Dialogs.showErrorDialog(e.getMessage());
            ComponentFileSaver saver = new ComponentFileSaver();
            try{
                saver.save();
            }catch (IOException exc){
                Dialogs.showErrorDialog(exc.getMessage());
            }

        }
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

    public static CarComponent getComponent(String component) {
        for (int i = 0; i < carComponents.size(); i++) {
            if (carComponents.get(i).getNavn().equals(component)) {
                return carComponents.get(i);
            }
        }
        return null;
    }

    private void writeObject(ObjectOutputStream s)throws IOException{
        s.defaultWriteObject();
        s.writeObject(new ArrayList<>(carComponents));
    }

    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException{
        List<CarComponent> list = (List<CarComponent>) s.readObject();
        carComponents = FXCollections.observableArrayList();
        carComponents.addAll(list);
    }
}
