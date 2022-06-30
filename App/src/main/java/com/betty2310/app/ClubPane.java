package com.betty2310.app;

import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ClubPane implements Initializable {
    public MFXTextField tfClubName;
    public MFXTextField tfClubCountry;

    public MFXComboBox<String> listLeague;
    public MFXComboBox<String> listTrophy;
    public MFXTextField tfClubCoach;
    public static String clubName;
    public static String countryName;
    public static String coachName;
    public static String leagueName;
    public static String trophyName;

    void setValueFilter() {
        clubName = tfClubName.getText();
        countryName = tfClubCountry.getText();
        coachName = tfClubCoach.getText();
        leagueName = listLeague.getValue();
        trophyName = listTrophy.getValue();
    }

    void setItemsListLeague() {
        listLeague.getItems().add("");
        listLeague.getItems().add("Nation League");
        listLeague.getItems().add("UEFA Champions League");
        listLeague.getItems().add("Serie A");
        listLeague.getItems().add("Premier League");
        listLeague.getItems().add("Carabao Cup");
        listLeague.getItems().add("La Liga");
        listLeague.getItems().add("DFB-Pokal");
        listLeague.getItems().add("UEFA Europa League");
        listLeague.getItems().add("Copa del Rey");
        listLeague.getItems().add("League 1");
        listLeague.getItems().add("Bundesliga");
        listLeague.getItems().add("FA Cup");
    }

    void setItemsListTrophy() {
        listTrophy.getItems().add("");
        listTrophy.getItems().add("CA Cup");
        listTrophy.getItems().add("ELP Cup");
        listTrophy.getItems().add("CRB Cup");
        listTrophy.getItems().add("C1 Cup");
        listTrophy.getItems().add("EPL Cup");
        listTrophy.getItems().add("C2 Cup");
        listTrophy.getItems().add("L1 Cup");
        listTrophy.getItems().add("L3 Cup");
        listTrophy.getItems().add("DB Cup");
        listTrophy.getItems().add("LLG Cup");
        listTrophy.getItems().add("FA Cup");
    }
    public void queryClubAction(ActionEvent event) {
        try {
            setValueFilter();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ClubOverview.fxml"));
            Parent window = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Club Overview");
            stage.setScene(new Scene(window));
            stage.show();
        } catch (IOException e) {
            System.out.println("Can't load Club Overview window.");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setItemsListLeague();
        setItemsListTrophy();
    }
}
