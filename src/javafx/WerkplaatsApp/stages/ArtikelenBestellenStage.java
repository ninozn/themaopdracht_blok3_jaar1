package javafx.WerkplaatsApp.stages;

import java.util.Optional;

import javafx.WerkplaatsApp.domein.Bedrijf;
import javafx.application.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class ArtikelenBestellenStage extends Stage {
	private Bedrijf hetBedrijf;
	private Stage stage;

	public ArtikelenBestellenStage(Bedrijf b) {
		hetBedrijf = b;

		Label labArtNummer = new Label("Voer artikelnummer in:");
		labArtNummer.setPrefWidth(300);
		labArtNummer.setPadding(new Insets(15, 0, 5, 10));
		labArtNummer.setStyle("-fx-font-size: 12; -fx-font-weight: bold");

		Label labAantal = new Label("Voer het aantal in:");
		labArtNummer.setPrefWidth(300);
		labArtNummer.setPadding(new Insets(15, 0, 5, 10));
		labAantal.setStyle("-fx-font-size: 12; -fx-font-weight: bold");

		TextField tfArtNummer = new TextField();
		tfArtNummer.setPrefWidth(300);

		TextField tfAantal = new TextField();
		tfAantal.setPrefWidth(300);

		Button annuleer = new Button("Annuleer");
		annuleer.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Waarschuwing!");
				alert.setHeaderText("Annuleren klantgegevens bewerken!");
				alert.setContentText("Weet u zeker dat u het bewerken van de klantgegevens wilt annuleren?\n\nDe wijzigingen zullen niet worden opgeslagen.");
				ButtonType annuleer = new ButtonType("Annuleer",
						ButtonData.CANCEL_CLOSE);
				ButtonType ok = new ButtonType("OK", ButtonData.APPLY);
				alert.getButtonTypes().setAll(annuleer, ok);
				Optional<ButtonType> result = alert.showAndWait();
				if (result.get() == annuleer) {
					alert.close();
				} 
				else {
					ArtikelenBestellenStage.this.close();
				}
			}
		});
		Button bestellen = new Button("Bestellen");
		bestellen.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Bestelling bevestigen");
				alert.setHeaderText("Controleer s.v.p. de gewijzigde gegevens:\n");
				alert.setContentText("\t\t" + "Artikelnummer: "+ tfArtNummer.getText() + "\t\t" + "\n\t\t"+ "Aantal: " + tfAantal.getText() + "\t\t"+ "\n\n\t\tZijn bovenstaande gegevens juist?");
				ButtonType annuleer = new ButtonType("Annuleer", ButtonData.CANCEL_CLOSE);
				ButtonType bevestigen = new ButtonType("Bevestigen", ButtonData.APPLY);
				alert.getButtonTypes().setAll(annuleer, bevestigen);
				if (!tfArtNummer.equals("") && !tfAantal.equals("")) {
						int artnummer = Integer.parseInt(tfArtNummer.getText());
						int aantal = Integer.parseInt(tfAantal.getText());
						hetBedrijf.maakBestelling(artnummer, aantal);
				}
				else {
					System.out.println("vul alle velden in");
					Alert a = new Alert(AlertType.CONFIRMATION);
					alert.setTitle("Alle Velden invullen svp");
					alert.setHeaderText("Controleer s.v.p. de ingevoerde gegevens:\n");
					alert.setContentText("\t\t" + "Artikelnummer: "
							+ tfArtNummer.getText() + "\t\t" + "\n\t\t"
							+ "Aantal: " + tfAantal.getText() + "\t\t"
							+ "\n\n\t\tZijn bovenstaande gegevens juist?");
					ButtonType ok = new ButtonType("Annuleer",
							ButtonData.CANCEL_CLOSE);
					alert.getButtonTypes().setAll(ok);
					Optional<ButtonType> result = alert.showAndWait();
					if (result.get() == ok) {
						a.close();
					}
				}
				Optional<ButtonType> result = alert.showAndWait();
				if (result.get() == annuleer) {
					alert.close();
				} else {

					alert.close();
					Alert success = new Alert(AlertType.INFORMATION);
					success.setTitle("Onderdeel succelvol besteld!");
					success.setHeaderText("Onderdeel succelvol besteld!");
					success.setContentText("Het onderdeel is succesvol toegevoegd aan de bestelling!"+ "\n\nWilt u terugkeren naar het hoofdmenu?");
					ButtonType bestellen = new ButtonType("Meer artikelen bestellen", ButtonData.NO);
					ButtonType hoofdmenu = new ButtonType("Terugkeren naar het hoofdmenu", ButtonData.APPLY);
					success.getButtonTypes().setAll(bestellen, hoofdmenu);

					Optional<ButtonType> result2 = success.showAndWait();
					if (result2.get() == bestellen) {
						tfArtNummer.setText("");
						tfAantal.setText("");
						success.close();
					}
					if (result2.get() == hoofdmenu){
						ArtikelenBestellenStage.this.close();
					}
				}
			}
		});
		VBox vbox = new VBox(5);
		vbox.getChildren().addAll(labArtNummer, tfArtNummer, labAantal,tfAantal);
		HBox hbox = new HBox(5);
		hbox.getChildren().addAll(annuleer, bestellen);
		hbox.setAlignment(Pos.CENTER);
		FlowPane flow = new FlowPane();
		flow.getChildren().addAll(vbox, hbox);
		Scene scene = new Scene(flow, 300, 270);
		setTitle("Artikelen bestellen");
		setScene(scene);
		show();
	}
	

	public static void main(String[] args) {
		Application.launch(args);
	}
}
