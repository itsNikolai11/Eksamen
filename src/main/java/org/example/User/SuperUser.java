package org.example.User;

import java.io.Serializable;

public class SuperUser implements Serializable {
    private String navn;
    private String passord;

    public SuperUser(String navn, String passord) {
        this.navn = navn;
        this.passord = passord;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public String getPassord() {
        return passord;
    }

    public void setPassord(String passord) {
        this.passord = passord;
    }
}
