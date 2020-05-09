package org.example.User;

import org.example.Dialogs;
import org.example.filbehandling.SuperUserFileOpener;
import org.example.filbehandling.SuperUserFileSaver;

import java.io.IOException;
import java.util.HashMap;

public class SuperUserManager {

    private static HashMap<String, SuperUser> superusers;

    private SuperUserManager() {
        SuperUserFileOpener opener = new SuperUserFileOpener();
        try {
            superusers = (HashMap<String, SuperUser>) opener.load();
            SuperUserFileSaver saver = new SuperUserFileSaver();
            try {
                saver.save();
            } catch (IOException exc) {
                Dialogs.showErrorDialog(exc.getMessage());
            }
            //TODO implementere validering her
        } catch (ClassNotFoundException | IOException e) {
            superusers = new HashMap<>();
            superusers.put("admin", new SuperUser("admin", "admin"));
            SuperUserFileSaver saver = new SuperUserFileSaver();
            try {
                saver.save();
            } catch (IOException exc) {
                Dialogs.showErrorDialog(exc.getMessage());
            }
        }

    }

    public static SuperUser getSuperUser(String brukernavn) {
        if (superusers == null) {
            new SuperUserManager();
        }
        return superusers.get(brukernavn);
    }

    public static HashMap getSuperUsers() {
        if (superusers == null) {
            new SuperUserManager();
        }
        return superusers;
    }
}
