package com.betty2310.app;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class LandingPage {
    public Button btClub;
    public Button btCoach;
    public Button btFootballer;
    @FXML
    private GridPane pnCoach;

    @FXML
    private GridPane pnClub;
    @FXML
    private GridPane pnFootballer;
    @FXML
    private Pane pnSection;
    @FXML
    private Label section;

    @FXML
    protected void handleClickSidebar(ActionEvent event) {
        if (event.getSource() == btFootballer) {
            section.setText("Footballer Dashboard");
            pnSection.setBackground(new Background(new BackgroundFill(Color.rgb(163, 190, 140), CornerRadii.EMPTY, Insets.EMPTY)));
            pnFootballer.toFront();
        }

        if (event.getSource() == btClub) {
            section.setText("Club Dashboard");
            pnSection.setBackground(new Background(new BackgroundFill(Color.rgb(94, 129, 172), CornerRadii.EMPTY, Insets.EMPTY)));
            pnClub.toFront();
        }
        if (event.getSource() == btCoach) {
            section.setText("Coach Dashboard");
            pnSection.setBackground(new Background(new BackgroundFill(Color.rgb(180, 142, 173), CornerRadii.EMPTY, Insets.EMPTY)));
            pnCoach.toFront();
        }
    }

    public void queryCoachAction(ActionEvent actionEvent) {
    }

    public void queryClubAction(ActionEvent actionEvent) {
    }

    public void queryFootballerAction(ActionEvent actionEvent) {
    }
}