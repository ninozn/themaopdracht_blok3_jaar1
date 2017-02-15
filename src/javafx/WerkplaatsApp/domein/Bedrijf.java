package javafx.WerkplaatsApp.domein;

import java.util.*;

import java.io.*;

public final class Bedrijf {

    /*
    removed redudant types
    moved to constructor
     */
    private Parkeerplaats parkeerplaays;
    private ArrayList<Werkorder> werkorders;
    private ArrayList<Dienst> diensten;
    private ArrayList<Klant> klanten;
    private ArrayList<Monteur> monteurs;
    private ArrayList<Factuur> facturen;
    private ArrayList<Betaling> betalingen;
    private ArrayList<Artikel> artikelen;
    private ArrayList<Auto> autos;
    private ArrayList<Bestelling> bestellingen;

    public Bedrijf() {
        this.bestellingen = new ArrayList<>();
        this.autos = new ArrayList<>();
        this.artikelen = new ArrayList<>();
        this.betalingen = new ArrayList<>();
        this.monteurs = new ArrayList<>();
        this.werkorders = new ArrayList<>();
        this.diensten = new ArrayList<>();
        this.klanten = new ArrayList<>();
        this.facturen = new ArrayList<>();
        /*
        MOVED to method
         */
        makeTestData();
    }

    /*
    change return and names
     */
    public ArrayList<Werkorder> getWerkorders(Monteur monteur) {
        return monteur.geefAlleWerkorder();
    }

    /*
    boolean and naming changed
     */
    public Werkorder zoekWerkorder(int werkorderNummer) {			//werkorder zoeken op getNextWerkOrderNummer
        for (Werkorder werkorder : this.werkorders) {
            if (werkorder.getWerkorderNummer() == werkorderNummer) {
                return werkorder;
            }
        }
        return null;
    }

    /*
    changed checksize and return 
     */
    public int getNextWerkOrderNummer() {				//werkordernummer genereren
        if (this.werkorders.isEmpty() || this.werkorders == null) {
            return 1;
        }
        return this.werkorders.size() + 1;
    }

    /*
    changed checksize and return 
     */
    public int getNextKlantNummer() {			//klantnummer genereren
        if (this.klanten.isEmpty() || this.klanten == null) {
            return 1;
        }
        return klanten.size() + 1;
    }

    /*
    boolean and naming changed
     */
    public boolean voegWerkOrderToe(Werkorder werkorder) { //werkorder aanmaken als deze nog niet bestaat
        if (!this.werkorders.contains(werkorder)) {
            return this.werkorders.add(werkorder);
        }
        return false;
    }

    public ArrayList geefMonteurs() {		//geef alle monteurs
        return this.monteurs;
    }

    /*
    boolean and naming changed
     */
    public Auto zoekAuto(String kenteken) {
        for (Auto auto : autos) {
            if (auto.getKenteken().equals(kenteken)) {
                return auto;
            }
        }
        return null;
    }

    /*
    boolean and naming changed
     */
    public Klant zoekKlantOpNummer(int klantNummer) { 	//zoek klant op klantnnummer
        for (Klant klant : klanten) {
            if (klant.getKlantnummer() == klantNummer) {
                return klant;
            }
        }
        return null;
    }

    /*
    boolean and naming changed
     */
    public Klant zoekKlant(Klant klant) {		//zoek klant op naw gegevens
        if (klanten.contains(klant)) {
            return klant;
        }
        return null;
    }

    /*
    boolean and naming changed
     */
    public boolean voegKlantToe(Klant klant) {		//voeg klant toe
        if (!klanten.contains(klant)) {
            return klanten.add(klant);
        }
        return false;
    }

    /*
    boolean and naming changed
     */
    public Artikel zoekArtikelOpNummer(int artikelNummer) {			//zoek artikel op nummer
        for (Artikel artikel : artikelen) {
            if (artikel.getArtNummer() == artikelNummer) {
                return artikel;
            }
        }
        return null;
    }

    /*
    boolean and naming changed
     */
    public boolean voegArtikelToe(Artikel artikel) {			//voeg artikel toe
        if (!artikelen.contains(artikel)) {
            return artikelen.add(artikel);
        }
        return false;
    }

    /*
    boolean and naming changed
     */
    public boolean verwijderArtikel(Artikel artikel) {			//verwijder artikel
        if (artikel != null) {
            return artikelen.remove(zoekArtikelOpNummer(artikel.getArtNummer()));
        }
        return false;
    }

    public boolean voegMonteurToe(Monteur nieuweMonteur) {		//monteur aanmaken
        boolean monteurIsAanwezig = true;
        if (nieuweMonteur != null) {
            for (Monteur monteur : monteurs) {
                if (monteur.equals(nieuweMonteur)) {
                    monteurIsAanwezig = false;
                }
            }
            if (monteurIsAanwezig) {
                return monteurs.add(nieuweMonteur);
            }
        }
        return true;
    }

    /*
    boolean and naming changed
     */
    public boolean zoekMonteur(int monteurNummer) {			//zoek monteur op monteurnummer
        for (Monteur monteur : monteurs) {
            if (monteur.getMonteurNummer() == monteurNummer) {
                return true;
            }
        }
        return false;
    }

    /*
    boolean return
     */
    public int bestelnummer() //genereer bestelnummer
    {
        return bestellingen.size() + 1;
    }

    /*
    naming changed
     */
    public boolean maakBestelling(int artikelNummer, int aantal) //  maak bestelling zet onderdeel op bestellijst
    {															//en voeg artikel bij aan de voorraad(alsof het geleverd is)
        Artikel artikel = (Onderdeel) zoekArtikelOpNummer(artikelNummer);
        Bestelling bestelling = new Bestelling(bestelnummer(), artikelNummer, aantal);
        this.bestellingen.add(bestelling);
        artikel.setAantal(aantal);

        return true;
    }

    public void save(Object obj) throws IOException //opslaan in een object zodat deze later weer kan worden ingelezen
    {
        if (obj instanceof Klant) {
            FileOutputStream ois = new FileOutputStream("Klanten.obj");
            ObjectOutputStream oos = new ObjectOutputStream(ois);
            for (Klant k : klanten) {
                oos.writeObject(k);
                System.out.println(k);
            }

            oos.close();
        }

        if (obj instanceof Auto) {
            FileOutputStream ois = new FileOutputStream("Autos.obj");
            ObjectOutputStream oos = new ObjectOutputStream(ois);
            for (Auto a : autos) {
                oos.writeObject(a);
            }
            oos.close();
        }

        if (obj instanceof Werkorder) {
            FileOutputStream ois = new FileOutputStream("Werkorder.obj");
            ObjectOutputStream oos = new ObjectOutputStream(ois);
            for (Werkorder w : werkorders) {
                oos.writeObject(w);
            }
            oos.close();
        }

        if (obj instanceof Artikel) {
            FileOutputStream ois = new FileOutputStream("Artikel.obj");
            ObjectOutputStream oos = new ObjectOutputStream(ois);
            for (Artikel a : artikelen) {
                oos.writeObject(a);
            }

            oos.close();
        }

        if (obj instanceof Monteur) {
            FileOutputStream ois = new FileOutputStream("Monteur.obj");
            ObjectOutputStream oos = new ObjectOutputStream(ois);
            for (Monteur m : monteurs) {
                oos.writeObject(m);
            }

            oos.close();
        }

        if (obj instanceof Factuur) {
            FileOutputStream ois = new FileOutputStream("Factuur.obj");
            ObjectOutputStream oos = new ObjectOutputStream(ois);
            for (Factuur f : facturen) {
                oos.writeObject(f);
            }

            oos.close();
        }

        if (obj instanceof Betaling) {
            FileOutputStream ois = new FileOutputStream("Betaling.obj");
            ObjectOutputStream oos = new ObjectOutputStream(ois);
            for (Betaling b : betalingen) {
                oos.writeObject(b);
            }

            oos.close();
        }

        if (obj instanceof Dienst) {
            FileOutputStream ois = new FileOutputStream("Diensten.obj");
            ObjectOutputStream oos = new ObjectOutputStream(ois);
            for (Dienst d : diensten) {
                oos.writeObject(d);
            }

            oos.close();
        }

        if (obj instanceof Parkeerplaats) {
            FileOutputStream ois = new FileOutputStream("Parkeerplaats.obj");
            ObjectOutputStream oos = new ObjectOutputStream(ois);
            oos.writeObject(parkeerplaays);
            oos.close();
        }

        if (obj instanceof Bestelling) {
            FileOutputStream ois = new FileOutputStream("Bestelling.obj");
            ObjectOutputStream oos = new ObjectOutputStream(ois);
            oos.writeObject(bestellingen);
            oos.close();
        }
    }

    public void readKlanten() throws IOException, ClassNotFoundException //inlezen van object klanten
    {
        FileInputStream fis = new FileInputStream("Klanten.obj");
        ObjectInputStream ois = new ObjectInputStream(fis);
        while (ois.readObject() != null) {
            Object obj = ois.readObject();
            Klant k = (Klant) obj;
            klanten.add(k);
        }
        ois.close();
    }

    public void readAutos() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream("Autos.obj");
        ObjectInputStream ois = new ObjectInputStream(fis);
        while (ois.readObject() != null) {
            Object obj = ois.readObject();
            Auto a = (Auto) obj;
            autos.add(a);
        }
        ois.close();
    }

    public void readWerkorders() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream("Werkorders.obj");
        ObjectInputStream ois = new ObjectInputStream(fis);
        while (ois.readObject() != null) {
            Object obj = ois.readObject();
            Werkorder w = (Werkorder) obj;
            werkorders.add(w);
        }
        ois.close();
    }

    public void readArtikelen() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream("Artikelen.obj");
        ObjectInputStream ois = new ObjectInputStream(fis);
        while (ois.readObject() != null) {
            Object obj = ois.readObject();
            Artikel a = (Artikel) obj;
            artikelen.add(a);
        }
        ois.close();
    }

    public void readMonteurs() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream("Monteurs.obj");
        ObjectInputStream ois = new ObjectInputStream(fis);
        while (ois.readObject() != null) {
            Object obj = ois.readObject();
            Monteur m = (Monteur) obj;
            monteurs.add(m);
        }
        ois.close();
    }

    public void readFacturen() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream("Facturen.obj");
        ObjectInputStream ois = new ObjectInputStream(fis);
        while (ois.readObject() != null) {
            Object obj = ois.readObject();
            Factuur f = (Factuur) obj;
            facturen.add(f);
        }
        ois.close();
    }

    public void readBetalingen() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream("Betalingen.obj");
        ObjectInputStream ois = new ObjectInputStream(fis);
        while (ois.readObject() != null) {
            Object obj = ois.readObject();
            Betaling b = (Betaling) obj;
            betalingen.add(b);
        }
        ois.close();
    }

    public void readDiensten() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream("Diensten.obj");
        ObjectInputStream ois = new ObjectInputStream(fis);
        while (ois.readObject() != null) {
            Object obj = ois.readObject();
            Dienst d = (Dienst) obj;
            diensten.add(d);
        }
        ois.close();
    }

    public void readParkeerplaats() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream("Parkeerplaats.obj");
        ObjectInputStream ois = new ObjectInputStream(fis);
        Object obj = ois.readObject();
        Parkeerplaats p = (Parkeerplaats) obj;
        parkeerplaays = p;
        ois.close();
    }

    public void readBestelling() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream("Bestelling.obj");
        ObjectInputStream ois = new ObjectInputStream(fis);
        Object obj = ois.readObject();
        Bestelling b = (Bestelling) obj;
        ois.close();
    }

    public String geefAlleArtikelen() {
        String s = "";
        for (Artikel a : artikelen) {
            s += a.toString() + "\n";
        }
        return s;
    }

    public String geefAlleBestellingen() {
        String s = "";
        for (Bestelling b : bestellingen) {
            s += b.toString() + "\n";

        }
        return s;

    }

    void makeTestData() {

// hieronder worden objecten aangemaakt zodat we kunnen testen
        Werkorder w1 = new Werkorder(getNextWerkOrderNummer(), "Algemene periodieke keuring");
        werkorders.add(w1);
        Monteur m1 = new Monteur("Bob de Bouwer", 30.00, 123);
        Monteur m2 = new Monteur("Felix de Fixer", 42.50, 456);
        Monteur m3 = new Monteur("Carlos de Car", 25.00, 789);
        Klant k1 = new Klant(111, "Hans", "Worst", "Straat", "0000AA", "Utrecht", 123, "a@a.nl");
        Auto a1 = new Auto("11-22-AA", "WOLOTGF35X2123456", "Ford", "Focus");
        Artikel ar1 = new Onderdeel("Band", 70.00, "Bandenhok", 1, 40);
        artikelen.add(ar1);
        Artikel ar2 = new Onderdeel("Motorolie 1L", 40.00, "F6", 2, 20);
        artikelen.add(ar2);
        Bestelling b1 = new Bestelling(1, 249, 300);
        bestellingen.add(b1);
        a1.setDeKlant(k1);
        Date dat = new Date();
        a1.setVolgendOnderhoud(dat);
        autos.add(a1);
        klanten.add(k1);
        monteurs.add(m1);
        monteurs.add(m2);
        monteurs.add(m3);
        Onderhoudsbeurt ob1 = new Onderhoudsbeurt(1, "Band vervangen", 0.25);
        diensten.add(ob1);
        Onderhoudsbeurt ob2 = new Onderhoudsbeurt(2, "Klein onderhoud", 1.00);
        diensten.add(ob2);
        Onderhoudsbeurt ob3 = new Onderhoudsbeurt(3, "Groot onderhoud", 3.00);
        diensten.add(ob3);
    }
}
