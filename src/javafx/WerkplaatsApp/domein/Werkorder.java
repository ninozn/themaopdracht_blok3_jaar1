package javafx.WerkplaatsApp.domein;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/*
    removed unused imports
 */
public class Werkorder implements Serializable {

    /*
    changed names to readable names
     */
    private int werkorderNummer;
    private Monteur monteur;
    private Date afspraakDatum;
    private String opmerking;
    private Auto auto;
    /*
    removed redudant type
    changed names to readable names
    moved to constructor
     */
    private ArrayList<ArtikelRegel> artikelen;

    /*
    changed names to readable names
     */
    public Werkorder(int werkworderNummer, String opmerking) {
        this.artikelen = new ArrayList<>();
        this.werkorderNummer = werkworderNummer;
        this.opmerking = opmerking;
    }

    /*
    changed getters and setters
     */
    public int getWerkorderNummer() {
        return werkorderNummer;
    }

    public void setWerkorderNummer(int werkorderNummer) {
        this.werkorderNummer = werkorderNummer;
    }

    public Monteur getMonteur() {
        return monteur;
    }

    public void setMonteur(Monteur monteur) {
        this.monteur = monteur;
    }

    public Date getAfspraakDatum() {
        return afspraakDatum;
    }

    public void setAfspraakDatum(Date afspraakDatum) {
        this.afspraakDatum = afspraakDatum;
    }

    public String getOpmerking() {
        return opmerking;
    }

    public void setOpmerking(String opmerking) {
        this.opmerking = opmerking;
    }

    public Auto getAuto() {
        return auto;
    }

    public void setAuto(Auto auto) {
        this.auto = auto;
    }

    public ArrayList<ArtikelRegel> getArtikelen() {
        return artikelen;
    }

    public void setArtikelen(ArrayList<ArtikelRegel> artikelen) {
        this.artikelen = artikelen;
    }

    /*
    changed methodname to understandable methodname 
  changed return method and removed extra code
     */
    public boolean voegArtikelToe(Artikel artikel, int aantal) {
        return artikelen.add(new ArtikelRegel(artikel, aantal));
    }

    /*
    change methodname
    and for loop to lambda*/
    public double getTotaalPrijsArtikelen() {
        double i = 0.0;
        return artikelen.stream().map((artikel) -> artikel.getHetArtikel().prijs).reduce(i, (accumulator, _item) -> accumulator + _item);
    }

    /*
    changed return method removed boolean
     */
    public boolean voegArtikelRegelToe(ArtikelRegel artikel) {
        if (!artikelen.contains(artikel)) {
            return artikelen.add(artikel);
        }
        return false;
    }

    /*
    + @Override
    changed return string
     */
    @Override
    public String toString() {
        return werkorderNummer + "\n" + auto.getKenteken() + " " + opmerking;
    }
}
