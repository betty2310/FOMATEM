/**
 *
 */
package com.betty2310.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Login to FOMATEM");
        stage.getIcons().add(new Image(String.valueOf(getClass().getResource("icon/ball.png"))));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}