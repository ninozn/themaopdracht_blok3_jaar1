package javafx.WerkplaatsApp.domein; 

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.util.Callback;

public class Werkorder implements Serializable {
	private int wonummer;
	private Monteur deMonteur;
	private Date afspraakDatum;
	private String opmerking; 
	private Auto deAuto;
	private ArrayList<ArtikelRegel> deArtikelRegels = new ArrayList<ArtikelRegel>();
	
	public Werkorder(int won, String o){
		wonummer = won;
		opmerking = o;
	}
	public Monteur getDeMonteur() {
		return deMonteur;
	}
	public void setDeMonteur(Monteur deMonteur) {
		this.deMonteur = deMonteur;
	}
	public Date getAfspraakDatum() {
		return afspraakDatum;
	}
	public void setAfspraakDatum(Date afspraakDatum) {
		this.afspraakDatum = afspraakDatum;
	}
	public int getWONummer(){
		return wonummer;
	}
	public String getOpmerking(){
		return opmerking;
	}
	public void setWONummer(int won){
		wonummer = won;
	}
	public void setOpmerking(String o){
		opmerking = o;
	}
	public Auto getDeAuto(){
		return deAuto;
	}
	public void setDeAuto(Auto a)
	{
		deAuto = a;	
	}
	public boolean voegToe(Artikel art,int aant)
	{
		ArtikelRegel a =new ArtikelRegel(art,aant);
		deArtikelRegels.add(a);
		return true;
	}
	public double getArtikelenPrijs()
	{
		double i=0.0;
		for(ArtikelRegel a:deArtikelRegels)
		{
			i+=a.getHetArtikel().prijs;
		}
		return i;
	}
	public boolean voegArtikelRegelToe(ArtikelRegel ar) {
		boolean b = false;
		if(!deArtikelRegels.contains(ar)){
			deArtikelRegels.add(ar);
			b = true;
		}
		return b;
	}
	public String toString(){
		//String datum = java.text.DateFormat.getDateInstance().format(afspraakDatum);
		String s = wonummer + "\n" + deAuto.getKenteken() + " " + opmerking;
		return s;
	}
}

