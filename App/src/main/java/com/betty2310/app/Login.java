package com.betty2310.app;

import com.betty2310.app.connection.Database;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

public class Login implements Initializable {
    public MFXTextField database;
    public MFXTextField user;
    public MFXTextField pass;

    public static String databaseName;
    public static String userName;
    public static String passName;
    public Label message;
    public MFXButton okButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // default value of database
        database.setText("footballer_manager");
    }

    private void setValue() {
        databaseName = database.getText();
        userName = user.getText();
        passName = pass.getText();
        if (passName == null) {
            passName = "";
        }
    }

    public void LoginAction() throws IOException {
        setValue();
        Connection conn = new Database().connection();
        if (conn != null) {
            message.setText("Connect to " + databaseName + "successfully!");
            message.setStyle("-fx-text-fill: green");

            Parent root = FXMLLoader.load(getClass().getResource("LandingPage.fxml"));
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("FOMATEM");
            stage.initStyle(StageStyle.UNIFIED);
            stage.getIcons().add(new Image(String.valueOf(getClass().getResource("icon/ball.png"))));
            stage.show();

            Stage loginStage = (Stage) okButton.getScene().getWindow();
            loginStage.close();
        } else {
            message.setText("Failed to connect to " + databaseName + ". Please check database, user, password again!");
            message.setStyle("-fx-text-fill: red");
        }
    }
}
