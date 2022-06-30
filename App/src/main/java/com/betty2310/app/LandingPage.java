package com.betty2310.app;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

import java.io.IOException;

public class LandingPage {
    public Button btClub;
    public Button btCoach;
    public Button btFootballer;
    public Pane pnHome;
    public Button btHome;
    @FXML
    private Pane pnCoach;
    @FXML
    private Pane pnClub;
    @FXML
    private Pane pnFootballer;
    @FXML
    protected void handleClickSidebar(ActionEvent event) throws IOException {
        if (event.getSource() == btFootballer) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FootballerPane.fxml"));
            Node node = fxmlLoader.load();
            pnFootballer.getChildren().add(node);
            pnFootballer.toFront();
        }

        if (event.getSource() == btClub) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ClubPane.fxml"));
            Node node = fxmlLoader.load();
            pnClub.getChildren().add(node);
            pnClub.toFront();
        }
        if (event.getSource() == btCoach) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CoachPane.fxml"));
            Node node = fxmlLoader.load();
            pnCoach.getChildren().add(node);
            pnCoach.toFront();
        }
        if (event.getSource() == btHome) {
            pnHome.toFront();
        }
    }

    public void returnHome(MouseEvent mouseEvent)  {
        if (mouseEvent.getClickCount() == 2 && !mouseEvent.isConsumed()) {
            pnHome.toFront();
        }
    }
}