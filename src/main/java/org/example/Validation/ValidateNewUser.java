package org.example.Validation;

import org.example.Exceptions.InvalidUserException;
import org.example.Exceptions.UserAlreadyExistsException;
import org.example.User.SuperUserManager;

public class ValidateNewUser {
    public static boolean validateUser(String name) throws UserAlreadyExistsException {
        if (SuperUserManager.getSuperUsers().get(name) != null) {
            throw new UserAlreadyExistsException("Dette navnet er allerede i bruk");
        }
        if (name.isBlank() || name.isEmpty()) {
            throw new IllegalArgumentException("Du må skrive inn et navn!");
        }

        return true;
    }

    public static boolean validatePasswords(String password, String confirmPassword) throws InvalidUserException {
        if (password.isEmpty() || password.isBlank()) {
            throw new InvalidUserException("Du må skrive inn et passord!");
        }
        if(confirmPassword.isBlank()||confirmPassword.isEmpty()){
            throw new InvalidUserException("Du må bekrefte passordet!");
        }
        if(!confirmPassword.equals(password)){
            throw new InvalidUserException("Passordene er ikke like.");
        }
        return true;
    }
}
