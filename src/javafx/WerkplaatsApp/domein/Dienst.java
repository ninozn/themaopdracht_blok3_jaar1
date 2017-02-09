package javafx.WerkplaatsApp.domein;

import java.io.Serializable;

public abstract class Dienst implements Serializable {
	protected String omschrijving;
	protected int nummer;
	public Dienst(int num, String om)
	{
		nummer = num;
		omschrijving = om;
	}
}
