package javafx.WerkplaatsApp.domein;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Monteur implements Serializable {

    private String naam;
    private double uurloon;
    private int monteurNummer;
    /*
    removed redudant type
        moved to constructor*/
    private ArrayList<Werkorder> werkorders;

    /**
     *
     * @param naam
     * @param uurloon
     * @param monteurNummer
     */
    public Monteur(String naam, double uurloon, int monteurNummer) {
        this.werkorders = new ArrayList<>();
        this.naam = naam;
        this.uurloon = uurloon;
        this.monteurNummer = monteurNummer;
    }

    /*
    changed return removed booleans
     */
    public boolean voegWerkOrderToe(Werkorder werkorder) {
        if (!werkorders.contains(werkorder) && werkorder != null) {
            return werkorders.add(werkorder);
        }
        return false;
    }

    /*
    +hashcode
    changed != null
    changed == equals
     */
    @Override
    public boolean equals(Object obj) { //overridden versie van methode equals in klasse monteur, om zo een heel monteur object te kunnen vergelijken
        boolean b = false;
        if (obj != null) {
            b = obj instanceof Monteur;
            b = b && (this.naam == null ? ((Monteur) obj).naam == null : this.naam.equals(((Monteur) obj).naam));
            b = b && (this.monteurNummer == ((Monteur) obj).monteurNummer);
            b = b && (this.uurloon == ((Monteur) obj).uurloon);
        }
        return b;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.naam);
        hash = 89 * hash + (int) (Double.doubleToLongBits(this.uurloon) ^ (Double.doubleToLongBits(this.uurloon) >>> 32));
        hash = 89 * hash + this.monteurNummer;
        return hash;
    }

    /*
    changed getters and setters
     */
    public String getNaam() {
        return this.naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public double getUurloon() {
        return this.uurloon;
    }

    public void setUurloon(double uurloon) {
        this.uurloon = uurloon;
    }

    public int getMonteurNummer() {
        return this.monteurNummer;
    }

    public void setMonteurNummer(int monteurNummer) {
        this.monteurNummer = monteurNummer;
    }

    public ArrayList<Werkorder> getWerkorders() {
        return this.werkorders;
    }

    public void setWerkorders(ArrayList<Werkorder> werkorders) {
        this.werkorders = werkorders;
    }

    public ArrayList<Werkorder> geefAlleWerkorder() { //methode om de hele arraylist van werkorders op te vragen die bij een monteur hoort
        return this.werkorders;
    }

    /*
    + @Override
    changed return */
    @Override
    public String toString() {
        return naam + " " + monteurNummer;
    }
}
