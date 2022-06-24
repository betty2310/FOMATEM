package com.betty2310.app;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class LandingPage {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}