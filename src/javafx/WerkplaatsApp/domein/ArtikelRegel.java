package javafx.WerkplaatsApp.domein;


public class ArtikelRegel  {
	private Artikel hetArtikel;
	private int aantal;
	
	public ArtikelRegel(Artikel a,int aant)
	{
		hetArtikel=a;
		aantal=aant;
	}
	
	public void setHetArtikel(Artikel hetArtikel) {
		this.hetArtikel = hetArtikel;
	}

	public Artikel getHetArtikel(){
		return hetArtikel;
	}
}
