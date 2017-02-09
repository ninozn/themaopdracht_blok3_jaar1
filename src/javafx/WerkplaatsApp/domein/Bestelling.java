package javafx.WerkplaatsApp.domein;
import java.io.Serializable;
import java.util.*;

public class Bestelling implements Serializable {
	private int bestelnummer,artikelnummer;
	private double aantal;
	private ArrayList<ArtikelRegel> deRegels = new ArrayList<ArtikelRegel>();
	
	public Bestelling(int nr, int artnr, int aant){
		bestelnummer = nr;
		artikelnummer=artnr;
		aantal=aant;
	}
	public int getBestelNummer(){
		return bestelnummer;
	}
	public void setBestelNummer(int bn){
		bestelnummer = bn;
	}
	public String toString(){
		String s = "Bestelnummer: "+bestelnummer+" artikelnummer: "+ artikelnummer+" aantal: "+ aantal;
		return s;
	}
}
