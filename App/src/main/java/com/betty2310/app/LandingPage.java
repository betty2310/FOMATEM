package com.betty2310.app;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class LandingPage {
    public Button btClub;
    public Button btCoach;
    public Button btFootballer;
    public GridPane pnHome;
    public Pane titlePane;
    @FXML
    private GridPane pnCoach;

    @FXML
    private GridPane pnClub;
    @FXML
    private GridPane pnFootballer;
    @FXML
    protected void handleClickSidebar(ActionEvent event) {
        if (event.getSource() == btFootballer) {
            btFootballer.setStyle("-fx-background-color: #ECEFF4");
            btClub.setStyle("-fx-background-color: #D8DEE9");
            btCoach.setStyle("-fx-background-color: #D8DEE9");
            pnFootballer.toFront();
        }

        if (event.getSource() == btClub) {
            btClub.setStyle("-fx-background-color: #ECEFF4");
            btFootballer.setStyle("-fx-background-color: #D8DEE9");
            btCoach.setStyle("-fx-background-color: #D8DEE9");
            pnClub.toFront();
        }
        if (event.getSource() == btCoach) {
            btCoach.setStyle("-fx-background-color: #ECEFF4");
            btClub.setStyle("-fx-background-color: #D8DEE9");
            btFootballer.setStyle("-fx-background-color: #D8DEE9");
            pnCoach.toFront();
        }
    }

    public void queryCoachAction(ActionEvent actionEvent) {
    }

    public void queryClubAction() {
        try {
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

    public void queryFootballerAction() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FootballerOverview.fxml"));
            Parent window = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Footballer Overview");
            stage.setScene(new Scene(window));
            stage.show();
        } catch (IOException e) {
            System.out.println("Can't load Footballer Overview window.");
        }

    }

    public void returnHome(MouseEvent mouseEvent) {
        if (mouseEvent.getClickCount() == 2 && !mouseEvent.isConsumed()) {
            btCoach.setStyle("-fx-background-color: #D8DEE9");
            btClub.setStyle("-fx-background-color: #D8DEE9");
            btFootballer.setStyle("-fx-background-color: #D8DEE9");
            pnHome.toFront();
        }
    }
}