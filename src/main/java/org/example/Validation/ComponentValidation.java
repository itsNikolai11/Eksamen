package org.example.Validation;

import org.example.Dialogs;

public class ComponentValidation {
    public static boolean validateComponent(String navn, String innPris, String kategori) {
        if (kategori == null) {
            throw new IllegalArgumentException("Du må velge en kategori!");
        }
        if (navn.isEmpty() || navn.isBlank()) {
            throw new IllegalArgumentException("Du må skrive inn et navn!");
        }
        if (innPris.isEmpty() || innPris.isBlank()) {
            throw new IllegalArgumentException("Du må skrive inn en pris!");
        }
        try {
        double pris = Double.parseDouble(innPris);
        if (pris < 0) {
            throw new IllegalArgumentException("Pris kan ikke være negativ!");
        }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(innPris + " er ikke en gyldig pris!");
            //Dialogs.showErrorDialog(innPris + " er ikke en gyldig pris!");
        }
        return true;
    }
}
