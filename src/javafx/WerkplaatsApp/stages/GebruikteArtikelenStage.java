package javafx.WerkplaatsApp.stages;

import javafx.WerkplaatsApp.domein.Artikel;
import javafx.WerkplaatsApp.domein.ArtikelRegel;
import javafx.WerkplaatsApp.domein.Bedrijf;
import javafx.WerkplaatsApp.domein.Werkorder;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GebruikteArtikelenStage extends Stage {
	private Bedrijf hetBedrijf;
	private Werkorder w = null;
	
	public GebruikteArtikelenStage(Bedrijf b){
		hetBedrijf = b;
		HBox hbox = new HBox(10);
		VBox labels = new VBox(20);
		VBox textfields = new VBox(10);
		Label labnr = new Label("artikelnummer: ");
		TextField tfnr = new TextField("");
		tfnr.setDisable(true);
		Label labant = new Label("aantal: ");
		TextField tfant = new TextField("");
		tfant.setDisable(true);
		Button toevoegen = new Button("Toevoegen aan werkorder");
		Button annuleer = new Button("Annuleer");
		toevoegen.setDisable(true);
		Label labwnr = new Label("wonummer: ");
		labwnr.setPrefWidth(100);
		TextField tfwnr = new TextField();
		tfwnr.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent ke) {
            	if(ke.getCode() == KeyCode.ENTER){
            		String s = tfwnr.getText();
            		int i = Integer.parseInt(s);
            		w = hetBedrijf.zoekWerkorder(i);
            		if(w != null){
            			tfnr.setDisable(false);
            			tfant.setDisable(false);
            			toevoegen.setDisable(false);
            		}
            		else{
            			Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("Fout");
						alert.setHeaderText("Geen zoekresultaat!");
						alert.setContentText("Er is geen werkorder gevonden met dit werkordernummer.\n\nControleer s.v.p. het ingevoerde werkordernummer.");
						alert.showAndWait();
            		}
            	}
            }
		});
		annuleer.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent e){
				GebruikteArtikelenStage.this.close();
			}
		});
		toevoegen.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent e){
        		String s = tfnr.getText();
        		int i = Integer.parseInt(s);
        		Artikel a = hetBedrijf.zoekArtikelOpNummer(i);
        		if(a != null){
        			String z = tfant.getText();
        			int zi = Integer.parseInt(z);
        			if(a.getAantal() >= zi){
        				int r = a.getAantal();
        				r = r - zi;
        				a.setAantal(r);
        				ArtikelRegel ar = new ArtikelRegel(a, zi);
            			w.voegArtikelRegelToe(ar);
            			Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("Goed");
						alert.setHeaderText("Toevoegen geslaagd");
						alert.setContentText("Dit artikel is succesvol op de ingevoerde werkorder toegevoed!");
						alert.showAndWait();
            			GebruikteArtikelenStage.this.close();
        			}
        			else{
        				Alert alert = new Alert(AlertType.ERROR);
    					alert.setTitle("Fout");
    					alert.setHeaderText("Te veel!");
    					alert.setContentText("Er is te weinig voorraad!\n\nControleer s.v.p. de invoer!");
    					alert.showAndWait();
        			}
        		}
        		else{
        			Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Fout");
					alert.setHeaderText("Geen zoekresultaat!");
					alert.setContentText("Er is geen artikel gevonden met dit artikelnummer.\n\nControleer s.v.p. het ingevoerde artikelnummer.");
					alert.showAndWait();
        		}
			}
		});
		labels.getChildren().addAll(labwnr, labnr, labant);
		textfields.getChildren().addAll(tfwnr, tfnr, tfant);
		hbox.getChildren().addAll(labels, textfields);
		HBox buttons = new HBox(10);
		buttons.getChildren().addAll(annuleer, toevoegen);
		VBox vbox = new VBox(10);
		vbox.getChildren().addAll(hbox, buttons);
		Scene scene = new Scene(vbox, 400, 250);
		setTitle("Gebruikt artikel");
		setResizable(false);
		setScene(scene);	
		show();
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}
