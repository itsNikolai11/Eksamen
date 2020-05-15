package org.example.Components;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

import java.io.Serializable;

public class CarComponent implements Serializable {
    private transient SimpleStringProperty navn;
    private transient SimpleDoubleProperty pris;
    private transient SimpleStringProperty kategori;

    public CarComponent(String navn, double pris, String kategori) {
        this.navn = new SimpleStringProperty(navn);
        this.pris = new SimpleDoubleProperty(pris);
        this.kategori = new SimpleStringProperty(kategori);
    }

    public String getNavn() {
        return navn.getValue();
    }

    public void setNavn(String navn) {
        this.navn = new SimpleStringProperty(navn);
    }

    public double getPris() {
        return pris.getValue();
    }

    public void setPris(double pris) {
        this.pris = new SimpleDoubleProperty(pris);
    }

    public String getKategori() {
        return kategori.getValue();
    }

    public void setKategori(String kategori) {
        this.kategori = new SimpleStringProperty(kategori);

    }
}
