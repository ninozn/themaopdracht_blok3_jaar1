package javafx.WerkplaatsApp.domein;

import java.io.Serializable;
import java.util.Objects;

public abstract class Artikel implements Serializable {

    protected String omschrijving;
    protected String locatie;
    protected double prijs;
    protected int artNummer;
    protected int aantal;

    public Artikel(String om, double pr, String loc, int art, int aant) {
        omschrijving = om;
        locatie = loc;
        prijs = pr;
        artNummer = art;
        aantal = aant;
    }

    public String getOmschrijving() {
        return omschrijving;
    }

    public void setOmschrijving(String omschrijving) {
        this.omschrijving = omschrijving;
    }

    public String getLocatie() {
        return locatie;
    }

    public void setLocatie(String locatie) {
        this.locatie = locatie;
    }

    public double getPrijs() {
        return prijs;
    }

    public void setPrijs(double prijs) {
        this.prijs = prijs;
    }

    public int getArtNummer() {
        return artNummer;
    }

    public void setAantal(int aantal) {
        this.aantal += aantal;
    }

    public int getAantal() {
        return aantal;
    }

    public void setArtNummer(int artNummer) {
        this.artNummer = artNummer;
    }

    @Override
    public boolean equals(Object obj) { //overridden versie van methode equals in klasse artikel, om zo een heel artikel object te kunnen vergelijken
        boolean b;
        b = obj instanceof Artikel;
        if (null!=obj) {
            b = (((Artikel) obj).artNummer == this.artNummer) && b;
            b = b && (this.omschrijving == null ? ((Artikel) obj).omschrijving == null : this.omschrijving.equals(((Artikel) obj).omschrijving));
            b = b && (this.locatie.equals(((Artikel) obj).locatie));
            b = b && (this.prijs == ((Artikel) obj).prijs);
        } else {
        }
        return b;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.omschrijving);
        hash = 31 * hash + Objects.hashCode(this.locatie);
        hash = 31 * hash + (int) (Double.doubleToLongBits(this.prijs) ^ (Double.doubleToLongBits(this.prijs) >>> 32));
        hash = 31 * hash + this.artNummer;
        return hash;
    }

    @Override
    public String toString() {
        return "Artikelnummer: " + artNummer + " aantal: " + aantal;
    }
}
