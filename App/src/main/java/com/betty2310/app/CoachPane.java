package com.betty2310.app;

import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class CoachPane {
    public MFXTextField tfCoachName;
    public MFXTextField tfCoachClub;
    public MFXTextField tfCoachFootballer;

    public static String coachName;
    public static String coachClub;
    public static String coachFootballer;

    public void setValueFilter() {
        coachClub = tfCoachClub.getText();
        coachName = tfCoachName.getText();
        coachFootballer = tfCoachFootballer.getText();
    }
    public void queryCoachAction(ActionEvent event) {
        try {
            setValueFilter();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CoachOverview.fxml"));
            Parent window = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Coach Overview");
            stage.setScene(new Scene(window));
            stage.show();
        } catch (IOException e) {
            System.out.println("Can't load Coach Overview window.");
        }
    }

    public void queryCoachLog(ActionEvent event) {
    }
}
