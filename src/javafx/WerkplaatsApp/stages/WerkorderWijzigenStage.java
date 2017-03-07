package javafx.WerkplaatsApp.stages;

import java.util.ArrayList;
import javafx.WerkplaatsApp.domein.Auto;
import javafx.WerkplaatsApp.domein.Bedrijf;
import javafx.WerkplaatsApp.domein.Klant;
import javafx.WerkplaatsApp.domein.Monteur;
import javafx.application.Application;
import javafx.WerkplaatsApp.domein.Werkorder;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class WerkorderWijzigenStage extends Stage {
	private Bedrijf hetBedrijf;
	private final Auto a;

	public WerkorderWijzigenStage(Bedrijf hetBedrijf3) {
        this.a = null;
		hetBedrijf = hetBedrijf3;

		Label labnaam = new Label("Naam:");
		labnaam.setPrefWidth(100);
		labnaam.setPadding(new Insets(15, 0, 5, 10));
		labnaam.setStyle("-fx-font-size: 12; -fx-font-weight: bold");

		Label labadr = new Label("Adres:");
		labadr.setPrefWidth(100);
		labadr.setPadding(new Insets(15, 0, 5, 10));
		labadr.setStyle("-fx-font-size: 12; -fx-font-weight: bold");

		Label labwp = new Label("Woonplaats:");
		labwp.setPrefWidth(100);
		labwp.setPadding(new Insets(15, 0, 5, 10));
		labwp.setStyle("-fx-font-size: 12; -fx-font-weight: bold");

		Label labtel = new Label("Telefoon nr:");
		labtel.setPrefWidth(100);
		labtel.setPadding(new Insets(15, 0, 5, 10));
		labtel.setStyle("-fx-font-size: 12; -fx-font-weight: bold");

		Label labken = new Label("Kenteken:");
		labken.setPrefWidth(100);
		labken.setPadding(new Insets(15, 0, 5, 10));
		labken.setStyle("-fx-font-size: 12; -fx-font-weight: bold");

		Label labmk = new Label("Merk:");
		labmk.setPrefWidth(100);
		labmk.setPadding(new Insets(15, 0, 5, 10));
		labmk.setStyle("-fx-font-size: 12; -fx-font-weight: bold");

		Label labmd = new Label("Model:");
		labmd.setPrefWidth(100);
		labmd.setPadding(new Insets(15, 0, 5, 10));
		labmd.setStyle("-fx-font-size: 12; -fx-font-weight: bold");

		Label labcn = new Label("Chassis nr:");
		labcn.setPrefWidth(100);
		labcn.setPadding(new Insets(15, 0, 5, 10));
		labcn.setStyle("-fx-font-size: 12; -fx-font-weight: bold");

		Label labdo = new Label("Datum OH:");
		labdo.setPrefWidth(100);
		labdo.setPadding(new Insets(15, 0, 5, 10));
		labdo.setStyle("-fx-font-size: 12; -fx-font-weight: bold");

		TextField tfmk = new TextField("");
		tfmk.setDisable(true);
		TextField tfmd = new TextField("");
		tfmd.setDisable(true);
		TextField tfdo = new TextField("");
		tfdo.setDisable(true);
		TextField tfnaam = new TextField("");
		tfnaam.setDisable(true);
		TextField tfadr = new TextField("");
		tfadr.setDisable(true);
		TextField tfwp = new TextField("");
		tfwp.setDisable(true);
		TextField tftel = new TextField("");
		tftel.setDisable(true);
		TextField tfcn = new TextField("");
		tfcn.setDisable(true);
		TextField tfken = new TextField("");
		tfken.setDisable(true);
		TextArea werkzhn = new TextArea(" ");
		werkzhn.setDisable(true);
		werkzhn.setPrefWidth(500);
		werkzhn.setPrefHeight(250);
		Button wijzigen = new Button("Wijzigen");
		Button annuleer = new Button("Annuleer");
		annuleer.setOnAction(e -> this.close());
		ComboBox cb = new ComboBox();
		ComboBox cb2 = new ComboBox();
		ArrayList<Monteur> Monteurs = hetBedrijf.geefMonteurs();
                Monteurs.forEach((m) -> {
                    cb.getItems().add(m);
            });
		Button kiesmon = new Button("kies monteur");
		Button kieswerko = new Button("kies werkorder");
		wijzigen.setDisable(true);
		wijzigen.setOnAction((ActionEvent e) -> {
                    Werkorder w = (Werkorder) cb2.getValue();
                    String s = werkzhn.getText();
                    w.setOpmerking(s);
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Goed");
                    alert.setHeaderText("Wijziging geslaagd!");
                    alert.setContentText("De werkzaamheden zijn succesvol gewijzigd!");
                    alert.showAndWait();
                    WerkorderWijzigenStage.this.close();
                });
		kiesmon.setOnAction((ActionEvent e) -> {
                    if (cb.getValue() instanceof Monteur && cb.getValue() != null) {
                        Monteur z = (Monteur) cb.getValue();
                        ArrayList<Werkorder> werkorders = hetBedrijf
                                .getWerkorders(z);
                        if (werkorders != null) {
                            werkorders.forEach((w) -> {
                                cb2.getItems().add(w);
                            });
                        }
                        kiesmon.setDisable(true);
                        kieswerko.setDisable(false);
                    } else {
                        Alert alert = new Alert(AlertType.ERROR);
                        alert.setTitle("Fout");
                        alert.setHeaderText("!");
                        alert.setContentText("Selecteer een juiste/bestaande monteur!");
                        alert.showAndWait();
                    }
                });
		kieswerko.setDisable(true);
		kieswerko.setOnAction((ActionEvent e) -> {
                    Werkorder w = (Werkorder) cb2.getValue();
                    Auto a1 = w.getAuto();
                    Klant k = a1.getDeKlant();
                    tfken.setText(a1.getKenteken());
                    tfmk.setText(a1.getMerk());
                    tfmd.setText(a1.getModel());
                    tfcn.setText(a1.getChassisnummer());
                    String q = a1.convertStringToDate(a1.getVolgendOnderhoud());
                    tfdo.setText(q);
                    tfnaam.setText(a1.getDeKlant().getVoornaam() + " " + a1.getDeKlant().getAchternaam());
                    tfadr.setText(a1.getDeKlant().getAdres());
                    tfwp.setText(a1.getDeKlant().getWoonplaats());
                    int i = a1.getDeKlant().getTelefoonNummer();
                    String z = Integer.toString(i);
                    tftel.setText(z);
                    werkzhn.setText(w.getOpmerking());
                    wijzigen.setDisable(false);
                    werkzhn.setDisable(false);
                });
		VBox textfields = new VBox(17);
		VBox labels = new VBox(10);
		VBox rechts = new VBox(5);
		VBox links = new VBox(40);
		links.getChildren().addAll(cb, kiesmon, cb2, kieswerko);
		HBox buttons = new HBox(10);
		rechts.setPadding(new Insets(0, 0, 10, 5));
		buttons.setPadding(new Insets(0, 0, 10, 5));
		textfields.getChildren().addAll(tfken, tfmk, tfmd, tfcn, tfdo, tfnaam,
				tfadr, tfwp, tftel);
		labels.getChildren().addAll(labken, labmk, labmd, labcn, labdo,
				labnaam, labadr, labwp, labtel);
		rechts.getChildren().addAll(werkzhn, buttons);
		buttons.getChildren().addAll(annuleer, wijzigen);
		HBox totaal = new HBox();
		totaal.getChildren().addAll(links, labels, textfields, rechts);
		Scene scene = new Scene(totaal, 1000, 500);
		setTitle("Werkorder wijzigen");
		setScene(scene);
		show();
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}