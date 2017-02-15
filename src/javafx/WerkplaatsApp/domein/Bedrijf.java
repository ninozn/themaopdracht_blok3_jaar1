package javafx.WerkplaatsApp.domein;
import java.util.*;

import javafx.WerkplaatsApp.domein.Artikel;

import java.io.*;

public class Bedrijf {
	private Parkeerplaats deParkeerplaats;
	private ArrayList<Werkorder> deWerkorders = new ArrayList<Werkorder>();
	private ArrayList<Dienst> deDiensten = new ArrayList<Dienst>();
	private ArrayList<Klant> deKlanten = new ArrayList<Klant>();
	private ArrayList<Monteur> deMonteurs = new ArrayList<Monteur>();
	private ArrayList<Factuur> deFacturen = new ArrayList<Factuur>();
	private ArrayList<Betaling> deBetalingen = new ArrayList<Betaling>();
	private ArrayList<Artikel> deArtikelen = new ArrayList<Artikel>();
	private ArrayList<Auto> deAutos = new ArrayList<Auto>();
	private ArrayList<Bestelling>deBestelling = new ArrayList<Bestelling>();
	
	public Bedrijf()	{ // hieronder worden objecten aangemaakt zodat we kunnen testen
		Werkorder w1 = new Werkorder(werkordernummer(), "Algemene periodieke keuring");
		deWerkorders.add(w1);
		Monteur m1 = new Monteur("Bob de Bouwer", 30.00, 123);
		Monteur m2 = new Monteur("Felix de Fixer", 42.50, 456);
		Monteur m3 = new Monteur("Carlos de Car", 25.00, 789);
		Klant k1 = new Klant(111, "Hans", "Worst", "Straat", "0000AA", "Utrecht",123,"a@a.nl");
		Auto a1 = new Auto("11-22-AA","WOLOTGF35X2123456","Ford","Focus");
		Artikel ar1 = new Onderdeel("Band", 70.00, "Bandenhok", 1, 40);
		deArtikelen.add(ar1);
		Artikel ar2 = new Onderdeel("Motorolie 1L", 40.00, "F6", 2, 20);
		deArtikelen.add(ar2);
		Bestelling b1 = new Bestelling(1, 249, 300);
		deBestelling.add(b1);
		a1.setDeKlant(k1);
		Date dat = new Date();
		a1.setVolgendOnderhoud(dat);
		deAutos.add(a1);
		deKlanten.add(k1);
		deMonteurs.add(m1);
		deMonteurs.add(m2);
		deMonteurs.add(m3);
		Onderhoudsbeurt ob1 = new Onderhoudsbeurt(1 ,"Band vervangen", 0.25);
		deDiensten.add(ob1);
		Onderhoudsbeurt ob2 = new Onderhoudsbeurt(2, "Klein onderhoud", 1.00 );
		deDiensten.add(ob2);
		Onderhoudsbeurt ob3 = new Onderhoudsbeurt(3, "Groot onderhoud", 3.00);
		deDiensten.add(ob3);
	}
	public ArrayList<Werkorder> geefAlleWerkorders(Monteur z) {  // alle werkorders weergeven
		ArrayList<Werkorder> hulp = null;
		hulp = z.geefAlleWerkorder();
		return hulp;
	}
	public Werkorder zoekWerkorder(int nr){			//werkorder zoeken op werkordernummer
		Werkorder gezochte = null;
		for(Werkorder w : deWerkorders){
			if(w.getWerkorderNummer()== nr){
				gezochte = w;
			}
		}
		return gezochte;
	}
	public int werkordernummer(){				//werkordernummer genereren
		int i;
		if(deWerkorders.size() == 0||deWerkorders == null){
			i = 1;
			return i;
		}
		i = deWerkorders.size() + 1;
		return i;
	}

	public int klantnummer(){			//klantnummer genereren
		int i;
		if(deKlanten.size() == 0 || deKlanten == null){
			i = 1;
			return i;
		}
		else
		{
			i = deKlanten.size() + 1;
		}
		return i;
	}
	public boolean voegWerkOrderToe(Werkorder w){ //werkorder aanmaken als deze nog niet bestaat
		boolean b = false;
		if(!deWerkorders.contains(w)){
			deWerkorders.add(w);
			b = true;
		}
		return b;
	}
	public ArrayList geefAlleMonteurs(){		//geef alle monteurs
		return deMonteurs;
	}
	public Auto zoekAuto(String s){
		Auto gezochte = null;
		for(Auto a : deAutos){
			if(a.getKenteken().equals(s)){
				gezochte = a;
			}
		}
		return gezochte;
	}
	
	public Klant zoekKlant(int nr){ 	//zoek klant op klantnnummer
		Klant gezochte = null;
		for(Klant k : deKlanten){
			if(k.getKlantnummer() == nr){
				gezochte = k;
			}
		}
		return gezochte;
	}
	
	public Klant zoekKlant(Klant k){		//zoek klant op naw gegevens
		Klant gezochte = null;
		if(deKlanten.contains(k)){
			gezochte = k;
		}		
		return gezochte;
	}
	
	public boolean voegKlantToe(Klant k){		//voeg klant toe
		boolean b = false;
		if(!deKlanten.contains(k)){
			deKlanten.add(k);
			b = true;
		}
		return b;
	}
	
	public Artikel zoekArtikel(int nr){			//zoek artikel op nummer
		Artikel gezochte = null;
		for(Artikel a : deArtikelen){
			if(a.getArtNummer() == nr){
				gezochte = a;
			}
		}
		return gezochte;
	}
	public boolean voegArtikelToe(Artikel a){			//voeg artikel toe
		boolean b = false;
		if(!deArtikelen.contains(a)){
			deArtikelen.add(a);
			b = true;
		}
		return b;
	}
	
	public boolean verwijderArtikel(Artikel a){			//verwijder artikel
		boolean b = false;
		Artikel hulp = zoekArtikel(a.getArtNummer());
		if(hulp != null){
			deArtikelen.remove(a);
			b = true;
		}
		return b;
	}
	
	public boolean voegMonteurToe(Monteur m){		//monteur aanmaken
		boolean b = true;
		Monteur hulp = m;
		if(hulp != null){
			for(Monteur mon : deMonteurs){
				if(m.equals(hulp)){
					b = false;
				}
			}
			if(b = true){
				deMonteurs.add(m);
			}
		}
		return b;
	}
	
	public boolean zoekMonteur(int nr){			//zoek monteur op monteurnummer
		boolean b = false;
		for(Monteur mon : deMonteurs){
			if(mon.getMonteurNummer() == nr){
				b = true;
			}
		}
		return b;
	}
	public int bestelnummer()		//genereer bestelnummer
	{
		int i =deBestelling.size()+1;
		return i;
	}
	public boolean maakBestelling(int artnr, int aantal)		//  maak bestelling zet onderdeel op bestellijst
	{															//en voeg artikel bij aan de voorraad(alsof het geleverd is)
		Artikel a = null;
		a = (Onderdeel) zoekArtikel(artnr);
		Bestelling b = new Bestelling(bestelnummer(),artnr,aantal);
		deBestelling.add(b);
		a.setAantal(aantal);

		return true;
	}
	
	
	public void save(Object obj) throws IOException				//opslaan in een object zodat deze later weer kan worden ingelezen
	{
		if (obj instanceof Klant)
		{
			FileOutputStream ois = new FileOutputStream("Klanten.obj");
			ObjectOutputStream oos = new ObjectOutputStream(ois);
			for(Klant k : deKlanten)
			{
				oos.writeObject(k);
				System.out.println(k);
			}
			
			oos.close();
		}
		
		if (obj instanceof Auto)			
		{
			FileOutputStream ois = new FileOutputStream("Autos.obj");
			ObjectOutputStream oos = new ObjectOutputStream(ois);
			for(Auto a : deAutos)
			{
				oos.writeObject(a);
			}
			oos.close();
		}
		
		if (obj instanceof Werkorder)
		{
			FileOutputStream ois = new FileOutputStream("Werkorder.obj");
			ObjectOutputStream oos = new ObjectOutputStream(ois);
			for(Werkorder w : deWerkorders)
			{
				oos.writeObject(w);
			}
			oos.close();
		}
		
		if (obj instanceof Artikel)
		{
			FileOutputStream ois = new FileOutputStream("Artikel.obj");
			ObjectOutputStream oos = new ObjectOutputStream(ois);
			for(Artikel a : deArtikelen)
			{
				oos.writeObject(a);
			}
			
			oos.close();
		}
		
		if (obj instanceof Monteur)
		{
			FileOutputStream ois = new FileOutputStream("Monteur.obj");
			ObjectOutputStream oos = new ObjectOutputStream(ois);
			for(Monteur m : deMonteurs)
			{
				oos.writeObject(m);
			}
			
			oos.close();
		}
		
	
		
		if (obj instanceof Factuur)
		{
			FileOutputStream ois = new FileOutputStream("Factuur.obj");
			ObjectOutputStream oos = new ObjectOutputStream(ois);
			for(Factuur f : deFacturen)
			{
				oos.writeObject(f);
			}
			
			oos.close();
		}
		
		if (obj instanceof Betaling)
		{
			FileOutputStream ois = new FileOutputStream("Betaling.obj");
			ObjectOutputStream oos = new ObjectOutputStream(ois);
			for(Betaling b : deBetalingen)
			{
				oos.writeObject(b);
			}
			
			oos.close();
		}
		
		if (obj instanceof Dienst)
		{
			FileOutputStream ois = new FileOutputStream("Diensten.obj");
			ObjectOutputStream oos = new ObjectOutputStream(ois);
			for(Dienst d : deDiensten)
			{
				oos.writeObject(d);
			}
			
			oos.close();
		}
		
		if (obj instanceof Parkeerplaats)
		{
			FileOutputStream ois = new FileOutputStream("Parkeerplaats.obj");
			ObjectOutputStream oos = new ObjectOutputStream(ois);
			oos.writeObject(deParkeerplaats);
			oos.close();
		}
		
		if (obj instanceof Bestelling)
		{
			FileOutputStream ois = new FileOutputStream("Bestelling.obj");
			ObjectOutputStream oos = new ObjectOutputStream(ois);
			oos.writeObject(deBestelling);
			oos.close();
		}	
	}	
	
	public void readKlanten() throws IOException, ClassNotFoundException		//inlezen van object klanten
	{
		FileInputStream fis = new FileInputStream("Klanten.obj");
		ObjectInputStream ois = new ObjectInputStream(fis);	
		while(ois.readObject() != null)
		{
			Object obj = ois.readObject();
			Klant k = (Klant)obj;
			deKlanten.add(k);
		}	
		ois.close();
	}
	
	public void readAutos() throws IOException, ClassNotFoundException
	{
		FileInputStream fis = new FileInputStream("Autos.obj");
		ObjectInputStream ois = new ObjectInputStream(fis);	
		while(ois.readObject() != null)
		{
			Object obj = ois.readObject();
			Auto a = (Auto)obj;
			deAutos.add(a);
		}	
		ois.close();
	}
	
	public void readWerkorders() throws IOException, ClassNotFoundException
	{
		FileInputStream fis = new FileInputStream("Werkorders.obj");
		ObjectInputStream ois = new ObjectInputStream(fis);	
		while(ois.readObject() != null)
		{
			Object obj = ois.readObject();
			Werkorder w = (Werkorder)obj;
			deWerkorders.add(w);
		}	
		ois.close();
	}
	
	public void readArtikelen() throws IOException, ClassNotFoundException
	{
		FileInputStream fis = new FileInputStream("Artikelen.obj");
		ObjectInputStream ois = new ObjectInputStream(fis);	
		while(ois.readObject() != null)
		{
			Object obj = ois.readObject();
			Artikel a = (Artikel)obj;
			deArtikelen.add(a);
		}	
		ois.close();
	}
	
	public void readMonteurs() throws IOException, ClassNotFoundException
	{
		FileInputStream fis = new FileInputStream("Monteurs.obj");
		ObjectInputStream ois = new ObjectInputStream(fis);	
		while(ois.readObject() != null)
		{
			Object obj = ois.readObject();
			Monteur m = (Monteur)obj;
			deMonteurs.add(m);
		}	
		ois.close();
	}
	
	
	public void readFacturen() throws IOException, ClassNotFoundException
	{
		FileInputStream fis = new FileInputStream("Facturen.obj");
		ObjectInputStream ois = new ObjectInputStream(fis);	
		while(ois.readObject() != null)
		{
			Object obj = ois.readObject();
			Factuur f = (Factuur)obj;
			deFacturen.add(f);
		}	
		ois.close();
	}
	
	public void readBetalingen() throws IOException, ClassNotFoundException
	{
		FileInputStream fis = new FileInputStream("Betalingen.obj");
		ObjectInputStream ois = new ObjectInputStream(fis);	
		while(ois.readObject() != null)
		{
			Object obj = ois.readObject();
			Betaling b = (Betaling)obj;
			deBetalingen.add(b);
		}	
		ois.close();
	}
	
	public void readDiensten() throws IOException, ClassNotFoundException
	{
		FileInputStream fis = new FileInputStream("Diensten.obj");
		ObjectInputStream ois = new ObjectInputStream(fis);	
		while(ois.readObject() != null)
		{
			Object obj = ois.readObject();
			Dienst d = (Dienst)obj;
			deDiensten.add(d);
		}	
		ois.close();
	}
	public void readParkeerplaats() throws IOException, ClassNotFoundException
	{
		FileInputStream fis = new FileInputStream("Parkeerplaats.obj");
		ObjectInputStream ois = new ObjectInputStream(fis);	
		Object obj = ois.readObject();
		Parkeerplaats p = (Parkeerplaats)obj;
		deParkeerplaats = p;	
		ois.close();
	}

	public void readBestelling() throws IOException, ClassNotFoundException
	{
		FileInputStream fis = new FileInputStream("Bestelling.obj");
		ObjectInputStream ois = new ObjectInputStream(fis);	
		Object obj = ois.readObject();
		Bestelling b = (Bestelling)obj;
		ois.close();
	}
	public String geefAlleArtikelen() {
		String s = "";
		for (Artikel a : deArtikelen) {
			s += a.toString() + "\n";
		}
		return s;
	}
	public String geefAlleBestellingen() {
		String s = "";
		for (Bestelling b : deBestelling) {
			s += b.toString() + "\n";

		}
		return s;

	}
}
