package javafx.WerkplaatsApp.stages;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

import javafx.WerkplaatsApp.domein.Auto;
import javafx.WerkplaatsApp.domein.Bedrijf;
import javafx.WerkplaatsApp.domein.Klant;
import javafx.WerkplaatsApp.domein.Monteur;
import javafx.application.Application;
import javafx.WerkplaatsApp.domein.Werkorder;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Callback;

public class WerkplaatsplanningStage extends Stage {

    private Bedrijf hetBedrijf;
    private Stage stage;
    private Auto a = null;

    public WerkplaatsplanningStage(Bedrijf hetBedrijf2) {
        hetBedrijf = hetBedrijf2;

        Werkorder w = new Werkorder(hetBedrijf.werkordernummer(), "");

        Label labwo = new Label("Werkorder:	");
        labwo.setPrefWidth(100);
        labwo.setPadding(new Insets(15, 0, 5, 10));
        labwo.setStyle("-fx-font-size: 12; -fx-font-weight: bold");

        Label labwon = new Label("" + w.getWerkorderNummer());
        labwon.setPadding(new Insets(15, 0, 5, 10));
        labwon.setStyle("-fx-font-size: 12; -fx-font-weight: bold");

        Label labop = new Label("						  Werkzaamheden: ");
        labop.setPadding(new Insets(15, 0, 5, 10));
        labop.setStyle("-fx-font-size: 12; -fx-font-weight: bold");

        HBox werkorder = new HBox(0);
        werkorder.getChildren().addAll(labwo, labwon, labop);

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
        TextArea werkzhn = new TextArea(" ");
        werkzhn.setDisable(true);
        werkzhn.setPrefWidth(500);
        werkzhn.setPrefHeight(250);
        Button toevoegen = new Button("Toevoegen");
        Button annuleer = new Button("Annuleer");
        annuleer.setOnAction(e -> this.close());
        DatePicker dp = new DatePicker();
        dp.setValue(LocalDate.now());
        final Callback<DatePicker, DateCell> dayCellFactory = new Callback<DatePicker, DateCell>() {
            @Override
            public DateCell call(final DatePicker datePicker) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);

                        if (item.isBefore(LocalDate.now())) {
                            setDisable(true);
                            setStyle("-fx-background-color: #ffc0cb;");
                        }
                    }
                };
            }
        };
        dp.setDayCellFactory(dayCellFactory);
        dp.setDisable(true);
        ComboBox cb = new ComboBox();
        ArrayList<Monteur> Monteurs = hetBedrijf.geefAlleMonteurs();
        for (Monteur m : Monteurs) {
            cb.getItems().add(m);
        }
        cb.setDisable(true);
        tfken.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent ke) {
                if (ke.getCode() == KeyCode.ENTER) {
                    String s = tfken.getText();
                    tfken.setText(s);
                    a = hetBedrijf.zoekAuto(s);
                    System.out.println(a);
                    if (a != null) {
                        tfmk.setText(a.getMerk());
                        tfmd.setText(a.getModel());
                        tfcn.setText(a.getChassisnummer());
                        String w = a.convertStringToDate(a
                                .getVolgendOnderhoud());
                        tfdo.setText(w);
                        tfnaam.setText(a.getDeKlant().getVoornaam() + " "
                                + a.getDeKlant().getAchternaam());
                        tfadr.setText(a.getDeKlant().getAdres());
                        tfwp.setText(a.getDeKlant().getWoonplaats());
                        int i = a.getDeKlant().getTelefoonNummer();
                        String z = Integer.toString(i);
                        tftel.setText(z);
                        werkzhn.setDisable(false);
                        toevoegen.setDisable(false);
                        dp.setDisable(false);
                        cb.setDisable(false);
                    } else {
                        Alert alert = new Alert(AlertType.ERROR);
                        alert.setTitle("Fout");
                        alert.setHeaderText("Geen zoekresultaat!");
                        alert.setContentText("Er is geen auto gevonden met dit kenteken.\n\nControleer s.v.p. het ingevoerde kenteken");
                        alert.showAndWait();
                    }
                }
            }
        });
        toevoegen.setDisable(true);
        toevoegen.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                String z = werkzhn.getText();
                w.setOpmerking(z);
                LocalDate ld = dp.getValue();
                Instant instant = ld.atStartOfDay()
                        .atZone(ZoneId.systemDefault()).toInstant();
                Date res = Date.from(instant);
                a.setVolgendOnderhoud(res);
                w.setAfspraakDatum(res);
                if (cb.getValue() instanceof Monteur && cb.getValue() != null) {
                    Monteur m = (Monteur) cb.getValue();
                    if (hetBedrijf.voegWerkOrderToe(w)) {
                        w.setMonteur(m);
                        m.voegWerkOrderToe(w);
                        hetBedrijf.voegWerkOrderToe(w);
                        w.setAuto(a);
                        Alert alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("Goed");
                        alert.setHeaderText("Toevoegen geslaagd");
                        alert.setContentText("Deze werkorder is succesvol toegevoed!");
                        alert.showAndWait();
                        WerkplaatsplanningStage.this.close();
                    } else {
                        Alert alert = new Alert(AlertType.ERROR);
                        alert.setTitle("Fout");
                        alert.setHeaderText("!");
                        alert.setContentText("Deze werkorder bestaat al!");
                        alert.showAndWait();
                        WerkplaatsplanningStage.this.close();
                    }
                }
            }
        });
        VBox textfields = new VBox(17);
        VBox labels = new VBox(10);
        VBox rechts = new VBox(5);
        HBox buttons = new HBox(10);
        rechts.setPadding(new Insets(0, 0, 10, 5));
        buttons.setPadding(new Insets(0, 0, 10, 5));
        textfields.getChildren().addAll(tfken, tfmk, tfmd, tfcn, tfdo, tfnaam,
                tfadr, tfwp, tftel);
        labels.getChildren().addAll(labken, labmk, labmd, labcn, labdo,
                labnaam, labadr, labwp, labtel);
        rechts.getChildren().addAll(werkzhn, dp, cb, buttons);
        buttons.getChildren().addAll(annuleer, toevoegen);
        HBox subtotaal = new HBox();
        subtotaal.getChildren().addAll(labels, textfields, rechts);
        VBox totaal = new VBox();
        totaal.getChildren().addAll(werkorder, subtotaal);
        Scene scene = new Scene(totaal, 1000, 500);
        setTitle("Werkplaats planning");
        setScene(scene);
        show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
