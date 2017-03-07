package javafx.WerkplaatsApp.stages;
import javafx.WerkplaatsApp.domein.Bedrijf;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
 
public class ArtikelStage extends Stage{
	private Bedrijf hetBedrijf;
	
	public ArtikelStage(Bedrijf b){
		hetBedrijf =b;
		String se = hetBedrijf.geefAlleArtikelen();
		FlowPane flow = new FlowPane();
		TextArea tA = new TextArea();
		tA.setText(se);
		Button b1 = new Button("OK");
		b1.setPadding(new Insets(0,150,30,0));
		b1.setOnAction(e->this.close());
		flow.getChildren().addAll(tA, b1);
		Scene scene = new Scene(flow, 300, 300);
		setTitle("Alle artikelen");
		setResizable(false);
		setScene(scene);	
		show();
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}
