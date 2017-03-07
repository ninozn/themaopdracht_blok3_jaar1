package javafx.WerkplaatsApp.stages;

import java.io.File;
import java.io.IOException;

import javafx.WerkplaatsApp.domein.Bedrijf;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HoofdmenuStage extends Application {

	private Button b1, b2, b3, b4, b5, b6, b7, b8, b9;
	private Label l1, l2, l3, l4, l5, l6, l7, l8, l9;
	private Bedrijf hetBedrijf = new Bedrijf();

	public void start(Stage stage) throws ClassNotFoundException, IOException {
		BorderPane pane = new BorderPane();

		b1 = new Button(); // buttons aanmaken
		b2 = new Button();
		b3 = new Button();
		b4 = new Button();
		b5 = new Button();
		b6 = new Button();
		b7 = new Button();
		b8 = new Button();
		b9 = new Button();

		b1.getStyleClass().add("klantToevoegen"); // is in css vormgegeven
		b2.getStyleClass().add("klantBewerken");
		b3.getStyleClass().add("artikelenBestellen");
		b4.getStyleClass().add("werkplaats");
		b5.getStyleClass().add("werkorderWijzigen");
		b6.getStyleClass().add("artikelen");
		b7.getStyleClass().add("bestellingen");
		b8.getStyleClass().add("gebruikteartikelen");
		b9.getStyleClass().add("exit");

		b1.setPrefSize(70, 70); // standaardhoogte en breedte van buttons zetten
		b2.setPrefSize(70, 70);
		b3.setPrefSize(70, 70);
		b4.setPrefSize(70, 70);
		b5.setPrefSize(70, 70);
		b6.setPrefSize(70, 70);
		b7.setPrefSize(70, 70);
		b8.setPrefSize(70, 70);
		b9.setPrefSize(70, 70);

		l1 = new Label("Klanten toevoegen"); // labels aanmaken
		l2 = new Label("Klant bewerken");
		l3 = new Label("Artikelen bestellen");
		l4 = new Label("Werkplaats planning");
		l5 = new Label("Werkorder wijzigen");
		l6 = new Label("Laat alle artikelen zien");
		l7 = new Label("Geef alle Bestellingen");
		l8 = new Label("Gebruikte artikelen");
		l9 = new Label("Sluit venster");

		b1.setOnAction(new EventHandler<ActionEvent>() { // klant toevoegen als
															// op b1 wordt
															// gedrukt
			@Override
			public void handle(ActionEvent e) {
				try {
					klantToevoegen();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		b2.setOnAction(new EventHandler<ActionEvent>() { // klant gegevens
															// bewerken als op
															// b2 wordt gedrukt
			@Override
			public void handle(ActionEvent e) {
				klantBewerken();
			}
		});
		b3.setOnAction(new EventHandler<ActionEvent>() { // artikelen bestellen,
															// zet artikel en
															// aantal op een
															// bestelling en
															// vult
			@Override
			public void handle(ActionEvent e) { // direct de voorraad aan(alsof
												// het geleverd is) als op b3
												// wordt gedrukt
				artikelenBestellen();
			}
		});
		b4.setOnAction(new EventHandler<ActionEvent>() { // werkorder toevoegen
															// als op b4 wordt
															// gedrukt
			@Override
			public void handle(ActionEvent e) {
				werkplaatsPlanning();
			}
		});
		b5.setOnAction(new EventHandler<ActionEvent>() { // bestaande werkorder
															// aanpassen als op
															// b5 wordt gedrukt
			@Override
			public void handle(ActionEvent e) {
				werkorderWijzigenStage();
			}
		});
		b6.setOnAction(new EventHandler<ActionEvent>() { // voorraad laten zien
															// als op b6 wordt
															// gedrukt
			@Override
			public void handle(ActionEvent e) {
				alleArtikelen();
			}

		});
		b7.setOnAction(new EventHandler<ActionEvent>() { // alle bestellingen
															// laten zien als op
															// b7 wordt gedrukt
			@Override
			public void handle(ActionEvent e) {
				alleBestellingen();
			}
		});
		b9.setOnAction(new EventHandler<ActionEvent>() { // sluiten programma
															// als op b9 wordt
															// gedrukt
			@Override
			public void handle(ActionEvent e) {
				stage.close();
			}
		});
		b8.setOnAction(new EventHandler<ActionEvent>() { // onderdelen
															// regristreren
															// laten zien als op
															// b8 wordt gedrukt
			@Override
			public void handle(ActionEvent e) {
				GebruikteArtikelenStage();
			}
		});
		Canvas canvas = new Canvas(400, 400); // aanmaken canvas voor plaatje
												// rode auto
		GraphicsContext gc = canvas.getGraphicsContext2D();
		Image afbeelding = new Image("auto.png");
		gc.drawImage(afbeelding, 0, -20, 400, 400);

		VBox vbox = new VBox(2); // aanmaken layout labels een buttons
		vbox.getChildren().addAll(b1, l1, b2, l2, b3, l3, b4, l4, b5, l5, b6,
				l6, b7, l7, b8, l8, b9, l9);
		vbox.setPadding(new Insets(30, 0, 0, 15));

		pane.setCenter(canvas);
		pane.setLeft(vbox);
		Scene scene = new Scene(pane, 1000, 1000);
		File cssSheet = new File("resource/stylesheet2.css"); // css path
																// aangeven
		scene.getStylesheets().add(cssSheet.toURI().toString());
		File icon = new File("resource/icon.png");
		stage.getIcons().add(new Image(icon.toURI().toString()));
		stage.setWidth(1000);
		stage.setHeight(1000);

		stage.setTitle("Hoofdmenu");
		stage.setScene(scene);
		stage.show();
	}

	public void klantToevoegen() throws IOException // methode om
													// klanttoevoegenstage te
													// maken en te openen
	{
		KlantToevoegenStage KlantToevoegen = new KlantToevoegenStage(hetBedrijf);
		KlantToevoegen.show();
	}

	public void klantBewerken() // methode om klantzoekenstage te maken en te
								// openen
	{
		KlantgegevensZoekenStage klantZoeken = new KlantgegevensZoekenStage(
				hetBedrijf);
		klantZoeken.show();
	}

	public void artikelenBestellen() // methode om artikelenbestellen te maken
										// en te openen
	{
		ArtikelenBestellenStage artikelenBestellen = new ArtikelenBestellenStage(
				hetBedrijf);
		artikelenBestellen.show();
	}

	public void werkplaatsPlanning() // methode om werkplaatsplanningstage te
										// maken en te openen
	{
		WerkplaatsplanningStage werkplaatsplanningStage2 = new WerkplaatsplanningStage(
				hetBedrijf);
		werkplaatsplanningStage2.show();
	}

	public void werkorderWijzigenStage() // methode om werkorderwijzigenstage te
											// maken en te openen
	{
		WerkorderWijzigenStage werkorderWijzigenStage = new WerkorderWijzigenStage(
				hetBedrijf);
		werkorderWijzigenStage.show();
	}

	public void alleArtikelen() { // methode om artikelstage te maken en te
									// openen
		ArtikelStage artikelStage = new ArtikelStage(hetBedrijf);
		artikelStage.show();
	}

	public void alleBestellingen() { // methode om bestellingenstage te maken en
										// te openen
		BestellingenStage bestellingStage = new BestellingenStage(hetBedrijf);
		bestellingStage.show();
	}

	public void GebruikteArtikelenStage() { // methode om
											// gebruikteartikelenstage te maken
											// en te openen
		GebruikteArtikelenStage gas = new GebruikteArtikelenStage(hetBedrijf);
		gas.show();
	}

	public static void main(String[] args) {
		Application.launch(args);
	}

}
