import org.example.Components.Kategorier;
import org.example.Validation.ComponentValidation;
import org.junit.Test;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

public class ComponentValidationTest {
    @Test
    public void price() {
        assertTrue(ComponentValidation.validateComponent("Audi", "100", Kategorier.Kategori.Bilmerke.name()));
        assertTrue(ComponentValidation.validateComponent("Audi", "430000", Kategorier.Kategori.Bilmerke.name()));
        assertThrows(IllegalArgumentException.class, () -> ComponentValidation.validateComponent("Audi", "adasds", Kategorier.Kategori.Bilmerke.name()));
        assertThrows(IllegalArgumentException.class, () -> ComponentValidation.validateComponent("Audi", "-11", Kategorier.Kategori.Bilmerke.name()));
        assertThrows(IllegalArgumentException.class, () -> ComponentValidation.validateComponent("Audi", "", Kategorier.Kategori.Bilmerke.name()));
        assertThrows(IllegalArgumentException.class, () -> ComponentValidation.validateComponent("Audi", " ", Kategorier.Kategori.Bilmerke.name()));
    }
}
