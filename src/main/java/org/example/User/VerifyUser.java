package org.example.User;

import org.example.Exceptions.InvalidLoginException;

public class VerifyUser {
    public static boolean verifyUser(String navn, String passord) throws InvalidLoginException {
        if (navn.isBlank() || navn.isEmpty()) {
            throw new InvalidLoginException("Du må skrive inn et brukernavn!");
        }
        if (passord.isEmpty() || passord.isBlank()) {
            throw new InvalidLoginException("Du må skrive inn et passord!");
        }
        /*SuperUserManager userManager = new SuperUserManager();
        SuperUser user = userManager.getSuperUser(navn);
        if (user == null) {
            throw new InvalidLoginException("Fant ikke brukeren " + navn);
        }*/
        return true;
    }
}
