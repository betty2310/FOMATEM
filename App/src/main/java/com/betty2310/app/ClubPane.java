package com.betty2310.app;

import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class ClubPane  {
    public MFXTextField tfClubName;
    public MFXTextField tfClubCountry;

    public static String clubName;
    public static String countryName;
    void setClubName(String name) {
        clubName = name;
    }
    void setCountryName(String name) {
        countryName = name;
    }

    public void queryClubAction(ActionEvent event) {
        try {
            setClubName(tfClubName.getText());
            setCountryName(tfClubCountry.getText());
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
}
