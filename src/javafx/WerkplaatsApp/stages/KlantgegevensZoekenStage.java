package javafx.WerkplaatsApp.stages;

import javafx.WerkplaatsApp.domein.Bedrijf;
import javafx.WerkplaatsApp.domein.Klant;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class KlantgegevensZoekenStage extends Stage {
	private Bedrijf hetBedrijf;
	private Stage stage;

	public KlantgegevensZoekenStage(Bedrijf b) {
		hetBedrijf = b;
		Label labzoeknr = new Label("Zoek op klantnummer");
		labzoeknr.setPrefWidth(300);
		labzoeknr.setPadding(new Insets(15, 0, 5, 10));
		labzoeknr.setStyle("-fx-font-size: 12; -fx-font-weight: bold");

		HBox klantnr = new HBox();
		klantnr.setPadding(new Insets(5, 0, 10, 10));
		Label labnr = new Label("Klantnummer");
		labnr.setPrefWidth(100);
		TextField tfnr = new TextField();
		klantnr.getChildren().addAll(labnr, tfnr);

		HBox knop1 = new HBox(10);
		Button annu1 = new Button("Annuleer");
		annu1.setOnAction(e -> this.close());
		Button zoeknr = new Button("Zoeken");
		zoeknr.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String s = tfnr.getText();
				int i = Integer.parseInt(s);
				Klant gezochteKlant = hetBedrijf.zoekKlantOpNummer(i);
				if (gezochteKlant != null) {
					tfnr.setText("");
					KlantBewerkenStage kbs = new KlantBewerkenStage(stage,
							gezochteKlant, hetBedrijf);
					kbs.showAndWait();
				} else {
					System.out.println(hetBedrijf);
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Fout");
					alert.setHeaderText("Geen zoekresultaat!");
					alert.setContentText("Er is geen klant gevonden met dit klantnummer.\n\nControleer s.v.p. het ingevoerde klantnummer");
					alert.showAndWait();
				}
			}
		});
		knop1.setPrefWidth(300);
		knop1.setAlignment(Pos.CENTER);
		knop1.getChildren().addAll(annu1, zoeknr);

		Label labzoeknaw = new Label("Zoeken op NAW-gegevens");
		labzoeknaw.setPrefWidth(300);
		labzoeknaw.setPadding(new Insets(15, 0, 5, 10));
		labzoeknaw.setStyle("-fx-font-size: 12; -fx-font-weight: bold");

		HBox vnaam = new HBox();
		vnaam.setPadding(new Insets(5, 0, 5, 10));
		Label labv = new Label("Voornaam");
		labv.setPrefWidth(100);
		TextField tfv = new TextField();
		vnaam.getChildren().addAll(labv, tfv);

		HBox anaam = new HBox();
		anaam.setPadding(new Insets(0, 0, 5, 10));
		Label laba = new Label("Achternaam");
		laba.setPrefWidth(100);
		TextField tfa = new TextField();
		anaam.getChildren().addAll(laba, tfa);

		HBox adres = new HBox();
		adres.setPadding(new Insets(0, 0, 5, 10));
		Label labadr = new Label("Adres");
		labadr.setPrefWidth(100);
		TextField tfadr = new TextField();
		adres.getChildren().addAll(labadr, tfadr);

		HBox plaats = new HBox();
		plaats.setPadding(new Insets(0, 0, 10, 10));
		Label labwp = new Label("Woonplaats");
		labwp.setPrefWidth(100);
		TextField tfwp = new TextField();
		plaats.getChildren().addAll(labwp, tfwp);

		HBox knop2 = new HBox(10);
		Button annu2 = new Button("Annuleer");
		annu2.setOnAction(e -> this.close());
		Button zoeknaw = new Button("Zoeken");
		zoeknaw.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				Klant k = new Klant(tfv.getText(), tfa.getText(), tfadr
						.getText(), tfwp.getText());
				Klant gezochte = hetBedrijf.zoekKlant(k);
				if (gezochte != null) {
					tfv.setText("");
					tfa.setText("");
					tfadr.setText("");
					tfwp.setText("");
					KlantBewerkenStage kbs = new KlantBewerkenStage(stage,
							gezochte, hetBedrijf);
					kbs.showAndWait();
				} else {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Fout");
					alert.setHeaderText("Geen zoekresultaat!");
					alert.setContentText("Er is geen klant gevonden met deze NAW-gegevens.\n\nControleer s.v.p. de ingevoerde NAW-gegevens.");
					alert.showAndWait();
				}
			}
		});
		knop2.setPrefWidth(300);
		knop2.setAlignment(Pos.CENTER);
		knop2.getChildren().addAll(annu2, zoeknaw);

		VBox control = new VBox();
		control.getChildren().addAll(labzoeknr, klantnr, knop1, labzoeknaw,vnaam, anaam, adres, plaats, knop2);

		Scene scene = new Scene(control, 300, 340);
		setTitle("Klantgegevens zoeken");
		setResizable(false);
		setScene(scene);
		show();
	}
}
