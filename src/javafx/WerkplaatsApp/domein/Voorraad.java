package javafx.WerkplaatsApp.domein;

public interface Voorraad {
	public abstract void werkVoorraadBij(int Artnummer) ;
	public abstract Artikel zoekArtikel(int zoek);
	public abstract Artikel zoekArtikel(String zoek);
	public abstract Artikel updateArtikel(int artGezocht, int aNr, String om, String aT, int aant, double pr, String loc);
	public abstract void nieuwArtikel(int aNr, String om, int aant, double pr, String loc, String aT);
	public abstract void opBestellingOnderdeel(int Art, int aantal);
	public abstract void opBestellingBrandstof(String type, double al, int TSIC);
}