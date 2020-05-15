package org.example.filbehandling;

import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import org.example.Components.CarComponent;
import org.example.Components.ComponentRegister;
import org.example.Dialogs;
import org.example.User.SuperUserManager;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ComponentFileSaver implements FileSaver {
    public void save(List<CarComponent> components) throws IOException {
        Alert alert = Dialogs.showSaveDialog();

        Thread saveThread = new Thread(() -> {
            try {
                OutputStream os = Files.newOutputStream(Paths.get("component.jobj"));
                ObjectOutputStream out = new ObjectOutputStream(os);
                out.writeObject(components);
                Thread.sleep(5000);

            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }
        });
        alert.show();
        saveThread.start();
        try {
            saveThread.join();
        } catch (InterruptedException e) {
            Dialogs.showErrorDialog(e.getMessage());
        }
        alert.close();

    }

    @Override
    public void save() throws IOException, InterruptedException {

    }
}
