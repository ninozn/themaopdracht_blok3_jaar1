package javafx.WerkplaatsApp.domein;

import java.io.Serializable;

public abstract class Artikel implements Serializable {
	protected String omschrijving;
	protected String locatie;
	protected double prijs;
	protected int artNummer;
	protected int aantal;
	public Artikel(String om, double pr, String loc, int art,int aant){
		omschrijving = om;
		locatie = loc;
		prijs = pr;
		artNummer = art;
		aantal=aant;
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
	public boolean equals(Object obj){ //overridden versie van methode equals in klasse artikel, om zo een heel artikel object te kunnen vergelijken
		boolean b;
		if(obj instanceof Artikel){
			b = true;
		}
		else{
			b = false;
		}
		b = b && (this.artNummer == ((Artikel)obj).artNummer);
		b = b && (this.omschrijving == ((Artikel)obj).omschrijving);
		b = b && (this.locatie == ((Artikel)obj).locatie);
		b = b && (this.prijs == ((Artikel)obj).prijs);
		return b;
	}
	public String toString()
	{
		return "Artikelnummer: "+artNummer+" aantal: "+aantal;
	}
}
