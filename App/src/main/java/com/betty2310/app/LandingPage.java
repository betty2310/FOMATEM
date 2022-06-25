package com.betty2310.app;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class LandingPage {
    @FXML
    private Label test = new Label();
    @FXML
    protected void FootballerActionMenu() {
        test.setText("You click footballer button!");
    }
    @FXML
    protected void ClubActionMenu() {
        test.setText("You click club button!");
    }
    @FXML
    protected void CoachActionMenu() {
        test.setText("You click coach button!");
    }
}