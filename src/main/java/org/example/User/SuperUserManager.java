package org.example.User;

import java.util.HashMap;

public class SuperUserManager {
    //TODO Singleton-prinsippet.
    private HashMap<String, SuperUser> superusers = new HashMap<>();

    public SuperUser getSuperUser(String brukernavn) {
        return superusers.get(brukernavn);
    }
}
