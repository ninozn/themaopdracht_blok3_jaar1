package javafx.WerkplaatsApp.domein;

import java.util.ArrayList;
import java.util.List;

public class Onderhoudsbeurt extends Dienst 
{
	private Monteur deMonteur;
	private ArrayList<ArtikelRegel> gebruikteArtikelen = new ArrayList<ArtikelRegel>();
	private double urenGeschat;
	
	public Onderhoudsbeurt(int num, String om, double uren){
		super(num, om);
		urenGeschat = uren;
	}
	
	public double getUrenGeschat(){
		return urenGeschat;
	}
	
	public Monteur getDeMonteur() {
		return deMonteur;
	}

	public void setDeMonteur(Monteur deMonteur) {
		this.deMonteur = deMonteur;
	}

	public List getGebruikteArtikelen() {
		return gebruikteArtikelen;
	}

	public String toString(){
		String s = "Gebruikte artikelen:\n ";
		for (ArtikelRegel a : gebruikteArtikelen)
		{
			s += "\n" + a;
		}
		
		return s;
	}
}
