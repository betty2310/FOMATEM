package com.betty2310.app;

import com.betty2310.app.connection.Database;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class FootballerDetail implements Initializable {
    public Label club;
    public Label coach;
    public Label name;
    public Connection db = new Database().connection();

    public String id = FootballerOverview.id;
    public Label name11;

    public String getName(String id) throws SQLException {
        String name = null;
        String query = "select name from footballer_overview where footballer_id = " + id + ";";
        ResultSet rs = db.createStatement().executeQuery(query);
        while (rs.next()) {
            name = rs.getString("name");
        }
        return name;
    }
    public String getClubName(String id) throws SQLException {
        String clubName = null;
        String query = "select club from footballer_overview where footballer_id = " + id + ";";
        ResultSet rs = db.createStatement().executeQuery(query);
        while (rs.next()) {
            clubName = rs.getString("club");
        }
        return clubName;
    }

    public String getCoachName(String id) throws SQLException {
        String name = null;
        String query = "select coach from footballer_overview where footballer_id = " + id + ";";
        ResultSet rs = db.createStatement().executeQuery(query);
        while (rs.next()) {
            name = rs.getString("coach");
        }
        return name;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String id = FootballerOverview.id;
        try {
            name.setText(getName(id));
            club.setText(getClubName(id));
            coach.setText(getCoachName(id));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
