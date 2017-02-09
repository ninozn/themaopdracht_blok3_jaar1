package javafx.WerkplaatsApp.domein;

public class Onderdeel extends Artikel{
	private int aantal;

	public Onderdeel(String om, double pr, String loc, int art, int d) {
		super(om, pr, loc, art, d);
		aantal = d;
	}

	public int getAantal() {
		return aantal;
	}

	
}
