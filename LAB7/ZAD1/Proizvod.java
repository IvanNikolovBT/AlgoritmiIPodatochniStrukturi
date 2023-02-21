package LAB7.ZAD1;

public class Proizvod {

    private double cena;
    private  int parchinja;
    private  String ime;
    private int prisuten;

    public Proizvod(int prisuten,double cena, int parchinja, String ime) {
        this.prisuten=prisuten;
        this.cena = cena;
        this.parchinja = parchinja;
        this.ime=ime;
    }


    public void zemi(int x)
    {
        this.parchinja-=x;
    }
    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public int getParchinja() {
        return parchinja;
    }

    public void setParchinja(int parchinja) {
        this.parchinja = parchinja;
    }
}
