import org.example.Exceptions.InvalidUserException;
import org.example.Exceptions.UserAlreadyExistsException;
import org.example.Validation.ValidateNewUser;
import org.junit.Test;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

public class ValidateNewUserTest {
    @Test
    public void name() throws UserAlreadyExistsException {
        assertTrue(ValidateNewUser.validateUser("ubruktNavn"));
        assertThrows(UserAlreadyExistsException.class, () -> ValidateNewUser.validateUser("admin"));
        assertThrows(IllegalArgumentException.class, () -> ValidateNewUser.validateUser(""));
        assertThrows(IllegalArgumentException.class, () -> ValidateNewUser.validateUser("  "));
    }

    @Test
    public void password() throws InvalidUserException {
        assertThrows(InvalidUserException.class, () -> ValidateNewUser.validatePasswords("", " "));
        assertThrows(InvalidUserException.class, () -> ValidateNewUser.validatePasswords(" ", " "));
        assertThrows(InvalidUserException.class, () -> ValidateNewUser.validatePasswords("test", "test2"));
        assertTrue(ValidateNewUser.validatePasswords("passord", "passord"));


    }
}
