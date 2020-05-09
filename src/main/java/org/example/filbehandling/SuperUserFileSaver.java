package org.example.filbehandling;

import javafx.scene.control.Alert;
import org.example.Dialogs;
import org.example.User.SuperUserManager;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SuperUserFileSaver implements FileSaver {


    @Override
    public void save() throws IOException {
        Thread saveThread = new Thread(() -> {
            try {
               // Alert alert = Dialogs.showSaveDialog();
                //alert.show();
                OutputStream os = Files.newOutputStream(Paths.get("users.jobj"));
                ObjectOutputStream out = new ObjectOutputStream(os);
                out.writeObject(SuperUserManager.getSuperUsers());
                Thread.sleep(1000);
                //alert.close();
                System.out.println("Lagring fullført");
            } catch (InterruptedException | IOException e) {
                System.out.println(e.getMessage());
            }
        });
        saveThread.start();
    }
}
