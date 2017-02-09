package javafx.WerkplaatsApp.domein;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Auto implements Serializable {
	private String kenteken,chassisnummer,merk,model, opmerking;
	private boolean afspraakGemaakt,onderhoudsPeriodeVoorbij;
	private Date laatsteBezoek,volgendOnderhoud;
	private Klant deKlant;

	public Auto(String ken,String chas,String mk,String mod)
	{
		kenteken = ken;
		chassisnummer = chas;
		merk = mk;
		model = mod;
		opmerking = "Nog geen opmerkingen bij deze auto!";
	}
	
	public String getOpmerking() {
		return opmerking;
	}

	public void setOpmerking(String opmerking) {
		this.opmerking = opmerking;
	}
	
	public String getKenteken() {
		return kenteken;
	}

	public String getChassisnummer() {
		return chassisnummer;
	}

	public String getMerk() {
		return merk;
	}

	public String getModel() {
		return model;
	}

	public boolean isAfspraakGemaakt() {
		return afspraakGemaakt;
	}

	public void setAfspraakGemaakt(boolean afspraakGemaakt) {
		this.afspraakGemaakt = afspraakGemaakt;
	}

	public boolean isOnderhoudsPeriodeVoorbij() {
		return onderhoudsPeriodeVoorbij;
	}

	public void setOnderhoudsPeriodeVoorbij(boolean onderhoudsPeriodeVoorbij) {
		this.onderhoudsPeriodeVoorbij = onderhoudsPeriodeVoorbij;
	}

	public Date getLaatsteBezoek() {
		return laatsteBezoek;
	}

	public void setLaatsteBezoek(Date laatsteBezoek) {
		this.laatsteBezoek = laatsteBezoek;
	}
	public Date getVolgendOnderhoud() {
		return volgendOnderhoud;
	}

	public void setVolgendOnderhoud(Date volgendOnderhoud) {
		this.volgendOnderhoud = volgendOnderhoud;
	}

	public Klant getDeKlant() {
		return deKlant;
	}

	public void setDeKlant(Klant deKlant) {
		this.deKlant = deKlant;
	}
	public String convertStringToDate(Date indate) //methode om een datum te kunnen omzetten naar een string
	{
	   String dateString = null;
	   SimpleDateFormat sdfr = new SimpleDateFormat("dd/MMM/yyyy");
	   try{
		dateString = sdfr.format( indate );
	   }catch (Exception ex ){
		System.out.println(ex);
	   }
	   return dateString;
	}
	public String toString() {
		return "Auto met kenteken:" + kenteken + " van het merk: " + merk
				+ " en model " + model + " heeft " + afspraakGemaakt != null
				|| afspraakGemaakt != false ? " geen afspraak gemaakt en is "
				+ laatsteBezoek + " voor het laatst geweest"
				: " een afspraak gemaakt op " + volgendOnderhoud + "";
	}
}