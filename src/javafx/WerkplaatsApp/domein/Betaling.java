package javafx.WerkplaatsApp.domein;

import java.io.Serializable;

public class Betaling implements Serializable {
	private int nummer,dagenVoorbij;
	private boolean alBetaald;
	public Betaling(int num,boolean aB){
		nummer = num;
		alBetaald =aB;
	}
	
	public int getNummer() {
		return nummer;
	}

	public int getDagenVoorbij() {
		return dagenVoorbij;
	}

	public void setDagenVoorbij(int dagenVoorbij) {
		this.dagenVoorbij = dagenVoorbij;
	}

	public boolean isAlBetaald() {
		return alBetaald;
	}

	public void setAlBetaald(boolean alBetaald) {
		this.alBetaald = alBetaald;
	}

	public String toString() {
		return "Betaling met nummer:"+nummer+" is"+alBetaald!=null||alBetaald!=true?" niet betaald":" wel betaald"+dagenVoorbij==null||dagenVoorbij!=0?"wel":"niet";
	}
}
