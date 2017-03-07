package javafx.WerkplaatsApp.domein;

import java.io.Serializable;

public class Factuur implements Serializable{
	private double manuren;
	private int facNummer;
	private Klant deKlant;
	private Werkorder deOrder;
	private Monteur deMonteur;
	private Betaling deBetaling;

	public Factuur(double man, int fac) {
		manuren = man;
		facNummer = fac;
	}

	public double getManuren() {
		return manuren;
	}

	public void setManuren(double manuren) {
		this.manuren = manuren;
	}

	public Werkorder getDeOrder() {
		return deOrder;
	}

	public void setDeOrder(Werkorder huidige) {
		deOrder = huidige;

	}
	
	public double berekenTotaalprijs() { //methoden om de totaalprijs te berekenen
		return ((manuren * deMonteur.getUurloon())+deOrder.getTotaalPrijsArtikelen());
	}

	public Klant getDeKlant() {
		return deKlant;
		
	}

	public void setDeKlant(Klant k) {
		deKlant =k;

	}

	
}
	