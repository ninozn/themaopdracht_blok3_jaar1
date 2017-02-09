package javafx.WerkplaatsApp.domein;

public class Brandstof extends Artikel{
	private int aantalLiters;
	private String TSICcode;

	public Brandstof(String om, double pr, String loc, int art, int al, String TSIC) {
		super(om, pr, loc, art, al);
		TSICcode = TSIC;
	}

	public int getAantalLiters() {
		return aantalLiters;
	}

	public void setAantalLiters(int aantalLiters) {
		this.aantalLiters = aantalLiters;
	}

	public String getTSICcode() {
		return TSICcode;
	}

	public void setTSICcode(String tSICcode) {
		TSICcode = tSICcode;
	}
}
