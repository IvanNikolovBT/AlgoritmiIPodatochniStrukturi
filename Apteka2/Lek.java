package Apteka2;

class Lek {
String ime;
int prisuten;
int cena;
int kolicina;

    public Lek(String ime, int prisuten, int cena, int kolicina) {
        this.ime = ime;
        this.prisuten = prisuten;
        this.cena = cena;
        this.kolicina = kolicina;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public int getPrisuten() {
        return prisuten;
    }

    public void setPrisuten(int prisuten) {
        this.prisuten = prisuten;
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
    public String toString() {
        if(prisuten==1)
        return ime+"\n"+"POZ\n"+cena+"\n"+kolicina;
        else return    ime+"\n"+"NEG\n"+cena+"\n"+kolicina;

    }
}
