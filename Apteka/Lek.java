package Apteka;

import java.util.Objects;

class Lek {
    String ime;
    int pozLista;
    int cena;
    int kolicina;

    public Lek(String ime, int pozLista, int cena, int kolicina) {
        this.ime = ime;
        this.pozLista = pozLista;
        this.cena = cena;
        this.kolicina = kolicina;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public int getPozLista() {
        return pozLista;
    }

    public void setPozLista(int pozLista) {
        this.pozLista = pozLista;
    }

    public int getCena() {
        return cena;
    }

    public void setCena(int cena) {
        this.cena = cena;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lek lek = (Lek) o;
        return pozLista == lek.pozLista && cena == lek.cena && kolicina == lek.kolicina && Objects.equals(ime, lek.ime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ime, pozLista, cena, kolicina);
    }

    @Override
    public String toString() {
       if (pozLista==1)
           return ime+"\n"+"POZ\n"+cena+"\n"+kolicina;
       else
           return ime+"\n"+"NEG\n"+cena+"\n"+kolicina;
    }
}
