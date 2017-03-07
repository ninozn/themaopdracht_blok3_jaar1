package javafx.WerkplaatsApp.stages;

import java.io.*;
import java.util.Optional;

import javafx.WerkplaatsApp.domein.Bedrijf;
import javafx.WerkplaatsApp.domein.Klant;
//import javafx.WerkplaatsApp.domein.Bedrijf;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class KlantToevoegenStage extends Stage {
	private Label labID, labID2, labv, laba, labadr, labpc, labwp, labtel, labmail;
	private TextField tfv, tfa, tfadr, tfpc, tfwp, tftel, tfmail;
	private Button annuleer, toevoegen;
	private Bedrijf hetBedrijf;

	public KlantToevoegenStage(Bedrijf b) throws IOException {
		hetBedrijf = b;
		HBox top = new HBox();
		top.setPrefWidth(100);
		top.setPadding(new Insets(5, 0, 0, 10));
		labID = new Label("Klantnr: ");
		labID.setPrefWidth(90);
		labID2 = new Label("" + hetBedrijf.getNextKlantNummer());
		top.getChildren().addAll(labID, labID2);

		VBox labels = new VBox(13);
		labels.setPrefWidth(100);
		labels.setPadding(new Insets(10, 0, 0, 10));
		labv = new Label("Voornaam");
		laba = new Label("Achternaam");
		labadr = new Label("Adres");
		labpc = new Label("Postcode");
		labwp = new Label("Woonplaats");
		labtel = new Label("Telefoon");
		labmail = new Label("E-mailadres");
		labels.getChildren().addAll(labv, laba, labadr, labpc, labwp, labtel, labmail);

		VBox texts = new VBox(5);
		texts.setPrefWidth(200);
		texts.setPadding(new Insets(5, 10, 0, 0));
		tfv = new TextField();
		tfa = new TextField();
		tfadr = new TextField();
		tfpc = new TextField();
		tfwp = new TextField();
		tftel = new TextField();
		tfmail = new TextField();
		texts.getChildren().addAll(tfv, tfa, tfadr, tfpc, tfwp, tftel, tfmail);

		HBox center = new HBox();
		center.getChildren().addAll(labels, texts);

		HBox kp = new HBox(10);
		kp.setPadding(new Insets(10, 10, 0, 0));
		kp.setAlignment(Pos.CENTER_RIGHT);
		kp.setPrefWidth(300);
		annuleer = new Button("Annuleer");
		annuleer.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Waarschuwing!");
				alert.setHeaderText("Annuleren klant toevoegen!");
				alert.setContentText("Weet u zeker dat u het toevoegen van een nieuwe klant wilt annuleren?\n\nDe gegevens zullen niet worden opgeslagen.");
				ButtonType annuleer = new ButtonType("Annuleer",
						ButtonData.CANCEL_CLOSE);
				ButtonType ok = new ButtonType("OK", ButtonData.APPLY);
				alert.getButtonTypes().setAll(annuleer, ok);
				Optional<ButtonType> result = alert.showAndWait();
				if (result.get() == annuleer) {
					alert.close();
				} else {
					KlantToevoegenStage.this.close();
				}
			}
		});
		toevoegen = new Button("Toevoegen");
		toevoegen.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Klantgegevens bevestigen");
				alert.setHeaderText("Controleer s.v.p. de ingevoerde gegevens:\n");
				alert.setContentText("\t\t" + labID.getText() + "\t\t\t"
						+ labID2.getText() + "\n\t\t" + labv.getText() + "\t\t"
						+ tfv.getText() + "\n\t\t" + laba.getText() + "\t\t"
						+ tfa.getText() + "\n\t\t" + labadr.getText()
						+ "\t\t\t" + tfadr.getText() + "\n\t\t"
						+ labpc.getText() + "\t\t\t" + tfpc.getText()
						+ "\n\t\t" + labwp.getText() + "\t\t" + tfwp.getText()
						+ "\n\t\t" + labtel.getText() + "\t\t\t"
						+ tftel.getText() + "\n\t\t" + labmail.getText()
						+ "\t\t" + tfmail.getText()
						+ "\n\n\t\tZijn bovenstaande gegevens juist?");
				ButtonType annuleer = new ButtonType("Annuleer",
						ButtonData.CANCEL_CLOSE);
				ButtonType bevestigen = new ButtonType("Bevestigen",
						ButtonData.APPLY);
				alert.getButtonTypes().setAll(annuleer, bevestigen);

				Optional<ButtonType> result = alert.showAndWait();
				if (result.get() == annuleer) {
					alert.close();
				} else {
					String s = labID2.getText();
					int i = Integer.parseInt(s);
					String s1 = tftel.getText();
					int i1 = Integer.parseInt(s1);
					Klant k = new Klant(i, tfv.getText(), tfa.getText(), tfadr
							.getText(), tfpc.getText(), tfwp.getText(), i1,
							tfmail.getText());
					hetBedrijf.voegKlantToe(k);
					System.out.println();
					try {
						hetBedrijf.save(k);
					} catch (IOException e1) {
					}

					alert.close();
					Alert success = new Alert(AlertType.INFORMATION);
					success.setTitle("Klant toegevoegd!");
					success.setHeaderText("Klant succesvol toegevoegd!");
					success.setContentText("De klant is succesvol toegevoegd aan het klantenbestand!"
							+ "\n\nWilt u terugkeren naar het hoofdmenu?");
					ButtonType toevoegen = new ButtonType(
							"Nog een klant toevoegen", ButtonData.NO);
					ButtonType hoofdmenu = new ButtonType(
							"Terugkeren naar het hoofdmenu", ButtonData.APPLY);
					success.getButtonTypes().setAll(toevoegen, hoofdmenu);

					Optional<ButtonType> result2 = success.showAndWait();
					if (result2.get() == toevoegen) {
						tfv.setText("");
						tfa.setText("");
						tfadr.setText("");
						tfpc.setText("");
						tfwp.setText("");
						tftel.setText("");
						tfmail.setText("");
						success.close();
						KlantToevoegenStage.this.close();
						KlantToevoegenStage kts;
						try {
							kts = new KlantToevoegenStage(hetBedrijf);
							kts.show();
						} catch (IOException e1) {
						}

					} else {
						KlantToevoegenStage.this.close();
					}
				}
			}
		});
		kp.getChildren().addAll(annuleer, toevoegen);

		VBox totaal = new VBox();
		totaal.getChildren().addAll(top, center, kp);

		Scene scene = new Scene(totaal, 320, 320);
		setTitle("Nieuwe klant toevoegen");
		setResizable(false);
		setScene(scene);
		show();
	}

}
