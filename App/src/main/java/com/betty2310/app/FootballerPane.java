package com.betty2310.app;

import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FootballerPane implements Initializable {
    @FXML
    private MFXComboBox<String> listAbility;
    @FXML
    private MFXComboBox<String> listFBfoot;
    @FXML
    private MFXComboBox<String> listRating;
    @FXML
    private MFXComboBox<String> listRole;
    @FXML
    private TextField tfAge;
    @FXML
    private MFXTextField tfFBClub;
    @FXML
    private MFXTextField tfFBCoach;
    @FXML
    private MFXTextField tfFBName;
    @FXML
    private MFXTextField tfFBNation;
    @FXML
    private TextField tfHeight;
    @FXML
    private TextField tfWeight;
    public static String ability;
    public static String foot;
    public static String rating;
    public static String role;
    public static String age;
    public static String club;
    public static String coach;
    public static String name;
    public static String nation;
    public static String height;
    public static String weight;

    public static String log = "";


    void setItemListAbility() {
        listAbility.getItems().add("offensive");
        listAbility.getItems().add("ball_control");
        listAbility.getItems().add("dribbling");
        listAbility.getItems().add("tight_possession");
        listAbility.getItems().add("low_pass");
        listAbility.getItems().add("lofted_pass");
        listAbility.getItems().add("finishing");
        listAbility.getItems().add("heading");
        listAbility.getItems().add("set_piece");
        listAbility.getItems().add("curl");
        listAbility.getItems().add("speed");
        listAbility.getItems().add("acceleration");
        listAbility.getItems().add("kicking_power");
        listAbility.getItems().add("jumping");
        listAbility.getItems().add("physical_contact");
        listAbility.getItems().add("balance");
        listAbility.getItems().add("stamina");
        listAbility.getItems().add("defensive_awareness");
        listAbility.getItems().add("tackling");
        listAbility.getItems().add("aggression");
        listAbility.getItems().add("defensive_engagement");
        listAbility.getItems().add("gk_awareness_ability");
        listAbility.getItems().add("gk_catching");
        listAbility.getItems().add("gk_parrying");
        listAbility.getItems().add("gk_reflexes");
        listAbility.getItems().add("gk_reach");
        listAbility.getItems().add("weak_foot_usage");
        listAbility.getItems().add("weak_foot_accuracy");
        listAbility.getItems().add("form");
        listAbility.getItems().add("injury_resistance");
        listAbility.getItems().add("");
    }

    void setItemListFoot() {
        listFBfoot.getItems().add("L");
        listFBfoot.getItems().add("R");
        listFBfoot.getItems().add("A");
        listFBfoot.getItems().add("");
    }

    void setItemListRole() {
        listRole.getItems().add("GK");
        listRole.getItems().add("CB");
        listRole.getItems().add("LB");
        listRole.getItems().add("RB");
        listRole.getItems().add("DMF");
        listRole.getItems().add("CMF");
        listRole.getItems().add("AMF");
        listRole.getItems().add("RMF");
        listRole.getItems().add("LMF");
        listRole.getItems().add("LWF");
        listRole.getItems().add("RWF");
        listRole.getItems().add("SS");
        listRole.getItems().add("CF");
    }

    void setItemListRate() {
        listRating.getItems().add("as_gk");
        listRating.getItems().add("as_lb");
        listRating.getItems().add("as_rb");
        listRating.getItems().add("as_cb");
        listRating.getItems().add("as_dmf");
        listRating.getItems().add("as_lmf");
        listRating.getItems().add("as_rmf");
        listRating.getItems().add("as_cmf");
        listRating.getItems().add("as_amf");
        listRating.getItems().add("as_lwf");
        listRating.getItems().add("as_rwf");
        listRating.getItems().add("as_ss");
        listRating.getItems().add("as_cf");
        listRating.getItems().add("");
    }

    void setValueFilter() {
        name = tfFBName.getText();
        nation = tfFBNation.getText();
        age = tfAge.getText();
        club = tfFBClub.getText();
        coach = tfFBCoach.getText();
        height = tfHeight.getText();
        weight = tfWeight.getText();
        ability = listAbility.getValue();
        foot = listFBfoot.getValue();
        role = listRole.getValue();
        rating = listRating.getValue();
    }
    public void queryFootballerAction(ActionEvent event) {
        try {
            setValueFilter();
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

    public void queryFootBallerLog(ActionEvent event) {
        try {
            log = "footballer_log";
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Log.fxml"));
            Parent window = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Footballer Log");
            stage.setScene(new Scene(window));
            stage.show();
            log = "";
        } catch (IOException e) {
            System.out.println("Can't load Footballer Log window.");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setItemListAbility();
        setItemListFoot();
        setItemListRole();
        setItemListRate();
    }
}
