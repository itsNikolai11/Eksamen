package org.example.filbehandling;

import javafx.scene.control.Alert;
import org.example.Dialogs;
import org.example.PrimaryController;
import org.example.User.SuperUserManager;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ConfigFileSaver implements FileSaver {
    @Override
    public void save() throws IOException, InterruptedException {
        Alert alert = Dialogs.showSaveDialog();

        Thread saveThread = new Thread(() -> {
            try {
                OutputStream os = Files.newOutputStream(Paths.get("configuration.jobj"));
                ObjectOutputStream out = new ObjectOutputStream(os);
                out.writeObject(PrimaryController.configuration);
                Thread.sleep(5000);

            } catch (InterruptedException | IOException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        });
        alert.show();
        saveThread.start();
        saveThread.join();
        alert.close();
    }
}
