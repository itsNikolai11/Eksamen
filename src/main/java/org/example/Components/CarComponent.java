package org.example.Components;

public class CarComponent {
    private String navn;
    private double pris;
    private Kategori kategori;

    public CarComponent(String navn, double pris, Kategori kategori) {
        this.navn = navn;
        this.pris = pris;
        this.kategori = kategori;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public double getPris() {
        return pris;
    }

    public void setPris(double pris) {
        this.pris = pris;
    }

    public Kategori getKategori() {
        return kategori;
    }

    public void setKategori(Kategori kategori) {
        this.kategori = kategori;
    }
}
