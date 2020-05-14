import org.example.Exceptions.InvalidLoginException;
import org.example.User.VerifyUser;
import org.junit.Test;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

public class VerifyUserTest {
    @Test
    public void username() throws InvalidLoginException {
        assertTrue(VerifyUser.verifyUser("admin", "admin"));
        assertThrows(InvalidLoginException.class,()-> VerifyUser.verifyUser("ugyldigNavn", "ugyldigPassord"));
        assertThrows(InvalidLoginException.class, ()-> VerifyUser.verifyUser("", ""));
        assertThrows(InvalidLoginException.class, ()-> VerifyUser.verifyUser("  ", "  "));

    }
}
