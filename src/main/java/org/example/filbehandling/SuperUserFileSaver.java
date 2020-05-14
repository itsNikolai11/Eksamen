package org.example.filbehandling;

import javafx.scene.control.Alert;
import org.example.Components.ComponentRegister;
import org.example.Dialogs;
import org.example.User.SuperUserManager;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SuperUserFileSaver implements FileSaver {
    @Override
    public void save() throws IOException {
        Alert alert = Dialogs.showSaveDialog();

        Thread saveThread = new Thread(() -> {
            try {

                    OutputStream os = Files.newOutputStream(Paths.get("users.jobj"));
                    ObjectOutputStream out = new ObjectOutputStream(os);
                    out.writeObject(SuperUserManager.getSuperUsers());
                    Thread.sleep(5000);
                    System.out.println("Lagring fullført");

            } catch (InterruptedException | IOException e) {
                System.out.println(e.getMessage());
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
}
