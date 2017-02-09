package javafx.WerkplaatsApp.stages;

import javafx.WerkplaatsApp.domein.Bedrijf;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class BestellingenStage extends Stage {
	private Bedrijf hetBedrijf;
	
	public BestellingenStage(Bedrijf b){
		hetBedrijf = b;
		String se = hetBedrijf.geefAlleBestellingen();
		FlowPane flow = new FlowPane();
		TextArea tA = new TextArea();
		tA.setText(se);
		Button b1= new Button("Sluit");
		b1.setOnAction(e->this.close());
		flow.getChildren().addAll(tA,b1);
		Scene scene = new Scene(flow, 400, 300);
		setTitle("Alle bestellingen");
		setResizable(false);
		setScene(scene);	
		show();	
	}
	public static void main(String[] args) {
		Application.launch(args);
	}
}