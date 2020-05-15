package org.example.Components;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class CarComponent implements Serializable {
    private SimpleStringProperty navn;
    private SimpleDoubleProperty pris;
    private SimpleStringProperty kategori;

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

    @Override
    public String toString() {
        return navn.getValue() + ";" + pris.getValue() + ";" + kategori.getValue();
    }

    private void writeObject(ObjectOutputStream s) throws IOException {
        s.writeUTF(navn.getValue());
        s.writeDouble(pris.getValue());
        s.writeUTF(kategori.getValue());
    }

    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        String navn = s.readUTF();
        double pris = s.readDouble();
        String kategori = s.readUTF();

        this.navn = new SimpleStringProperty(navn);
        this.pris = new SimpleDoubleProperty(pris);
        this.kategori = new SimpleStringProperty(kategori);

    }
}
