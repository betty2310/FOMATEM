package com.betty2310.app;

import com.betty2310.app.connection.Database;
import com.betty2310.app.model.ClubOverviewTable;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ClubDetail implements Initializable {
    public Label clubName;
    public Label clubRegion;
    public Label clubLeague;

    public Connection db = new Database().connection();

    public String getClubName(String id) throws SQLException {
        String clubName = null;
        String query = "select name from club where club_id = " + id + ";";
        ResultSet rs = db.createStatement().executeQuery(query);
        while (rs.next()) {
            clubName = rs.getString("name");
        }
        return clubName;
    }

    public String getClubLeague(String id) throws SQLException {
        StringBuilder clubLeague = new StringBuilder();
        String query = "select league from club_league where club_id = " + id + ";";
        ResultSet rs = db.createStatement().executeQuery(query);
        while (rs.next()) {
            clubLeague.append(rs.getString("league"));
            clubLeague.append(", ");
        }
        clubLeague.deleteCharAt(clubLeague.length() - 2);
        return clubLeague.toString();
    }

    public String getClubCountry(String id) throws SQLException { String clubCountry = null; String query = "select country from club where club_id = " + id + ";";
        ResultSet rs = db.createStatement().executeQuery(query);
        while (rs.next()) {
            clubCountry = rs.getString("country");
        }
        return clubCountry;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String clubID = ClubOverview.clubID;
        try {
            clubName.setText(getClubName(clubID));
            clubRegion.setText(getClubCountry(clubID));
            clubLeague.setText(getClubLeague(clubID));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
