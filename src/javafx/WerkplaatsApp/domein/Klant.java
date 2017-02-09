package javafx.WerkplaatsApp.domein;

import java.io.Serializable;

public class Klant implements Serializable {
	private int klantnr;
	private String voornaam, achternaam, adres, postcode, woonplaats, email;
	private int telefoonNummer;
	//twee verschillende constructors omdat niet alle gegevens van een klant hoeven te worden ingevuld
	public Klant(String vn, String an, String ad, String wp) 
	{
		voornaam = vn;
		achternaam = an;
		adres = ad;
		woonplaats = wp;
	}
	public Klant(int knr, String vn, String an, String ad, String zip, String wp, int tel, String em) {
		klantnr = knr;
		voornaam = vn;
		achternaam = an;
		adres = ad;
		postcode = zip;
		woonplaats = wp;
		email = em;
		telefoonNummer = tel;
	}

	public int getKlantnummer()
	{
		return klantnr;
	}
	
	public void setKlantnummer(int knr)
	{
		klantnr = knr;
	}
	
	public String getVoornaam() {
		return voornaam;
	}

	public void setVoornaam(String voornaam) {
		this.voornaam = voornaam;
	}

	public String getAchternaam() {
		return achternaam;
	}

	public void setAchternaam(String achternaam) {
		this.achternaam = achternaam;
	}

	public String getAdres() {
		return adres;
	}

	public void setAdres(String adres) {
		this.adres = adres;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getWoonplaats() {
		return woonplaats;
	}

	public void setWoonplaats(String woonplaats) {
		this.woonplaats = woonplaats;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getTelefoonNummer() {
		return telefoonNummer;
	}

	public void setTelefoonNummer(int telefoonNummer) {
		this.telefoonNummer = telefoonNummer;
	}
	public boolean equals(Object andere) {//overridden versie van methode equals in klasse klant, om zo een heel klant object te kunnen vergelijken
		boolean b;
		if (andere instanceof Klant) {
			b = true;
		}
		else {
			b = false;
		}
		
		b = b && (this.voornaam.equals(((Klant)andere).voornaam)); 
		b = b && (this.achternaam.equals(((Klant)andere).achternaam));
		b = b && (this.adres.equals(((Klant)andere).adres));             
		b = b && (this.woonplaats.equals(((Klant)andere).woonplaats));     
		
		return b;
	}

	public String toString() {
		String tel = " het telefoonnummer is: " + telefoonNummer + " ";
		if (telefoonNummer == 0) {
			tel = " er is geen telefoonnummer bekend ";
		}
		String em = " en het emailadres is: " + email;
		if (email.isEmpty()) {							//isEmpty is niet zeker
			em = " en er is geen emailadres bekend ";
		}
		return voornaam + " " + achternaam + "(klantnummer " + klantnr +") woont op " + adres+ " met postcode " + postcode + " in " + woonplaats + tel + em;
	}

}
