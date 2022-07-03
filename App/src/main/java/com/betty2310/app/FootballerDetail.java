package com.betty2310.app;

import com.betty2310.app.connection.Database;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

public class FootballerDetail implements Initializable {
    public Label club;
    public Label coach;
    public Label name;
    public Label foot;
    public Label skill;
    @FXML
    private Label age;

    @FXML
    private Label amf;

    @FXML
    private Label cb;

    @FXML
    private Label cf;

    @FXML
    private Label clubs;

    @FXML
    private Label cmf;

    @FXML
    private Label coachs;

    @FXML
    private Label country;

    @FXML
    private Label dmf;

    @FXML
    private Label dob;

    @FXML
    private Label gk;

    @FXML
    private Label height;

    @FXML
    private Label lb;

    @FXML
    private Label leagues;

    @FXML
    private Label lmf;

    @FXML
    private Label lwf;

    @FXML
    private Label price;
    @FXML
    private Label rb;

    @FXML
    private Label rmf;

    @FXML
    private Label rwf;

    @FXML
    private Label ss;

    @FXML
    private Label trophys;

    @FXML
    private Label weight;
    public Connection db = new Database().connection();

    public String id = FootballerOverview.id;

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

    public String getAge(String id) throws SQLException {
        String age = null;
        String query = "select age from footballer_overview where footballer_id = " + id + ";";
        ResultSet rs = db.createStatement().executeQuery(query);
        while (rs.next()) {
            age = rs.getString("age");
        }
        return age;
    }

    public String getDob(String id) throws SQLException {
        String str = null;
        String query = "select dob from footballer where footballer_id = " + id + ";";
        ResultSet rs = db.createStatement().executeQuery(query);
        while (rs.next()) {
            str = rs.getString("dob");
        }
        return str;
    }

    public String getHeight(String id) throws SQLException {
        String str = null;
        String query = "select height from footballer_overview where footballer_id = " + id + ";";
        ResultSet rs = db.createStatement().executeQuery(query);
        while (rs.next()) {
            str = rs.getString("height");
        }
        return str;
    }

    public String getWeight(String id) throws SQLException {
        String str = null;
        String query = "select weight from footballer_overview where footballer_id = " + id + ";";
        ResultSet rs = db.createStatement().executeQuery(query);
        while (rs.next()) {
            str = rs.getString("weight");
        }
        return str;
    }

    public String getCountry(String id) throws SQLException {
        String str = null;
        String query = "select nationality from footballer_overview where footballer_id = " + id + ";";
        ResultSet rs = db.createStatement().executeQuery(query);
        while (rs.next()) {
            str = rs.getString("nationality");
        }
        return str;
    }

    public String getPrice(String id) throws SQLException {
        String str = null;
        String query = "select price from footballer_overview where footballer_id = " + id + ";";
        ResultSet rs = db.createStatement().executeQuery(query);
        while (rs.next()) {
            str = rs.getString("price");
        }
        return str;
    }

    public String getSkills(String id) throws SQLException {
        StringBuilder str = new StringBuilder();
        String query = "select skill from footballer_skill where footballer_id = " + id + ";";
        ResultSet rs = db.createStatement().executeQuery(query);
        int i = 1;
        while (rs.next()) {
            str.append(i).append(", ").append(rs.getString("skill")).append("\n");
            ++i;
        }
        return str.toString();
    }

    public String getFoot(String id) throws SQLException {
        String str = "";
        String query = "select stronger_foot from footballer_overview where footballer_id = " + id + ";";
        ResultSet rs = db.createStatement().executeQuery(query);
        while (rs.next()) {
            str = rs.getString("stronger_foot");
        }

        if (Objects.equals(str, "L")) {
            str = "Left";
        }
        if (Objects.equals(str, "R")) {
            str = "Right";
        }
        if (Objects.equals(str, "A")) {
            str = "All";
        }
        return str;
    }

    public void setPosition(String id) throws SQLException {
        String query = "select position from footballer_position where footballer_id = " + id + ";";
        ResultSet rs = db.createStatement().executeQuery(query);
        while (rs.next()) {
            String str = rs.getString("position");
            if (Objects.equals(str, "GK")) {
                gk.setOpacity(1);
            }
            if (Objects.equals(str, "CB")) {
                cb.setOpacity(1);
            }
            if (Objects.equals(str, "RB")) {
                rb.setOpacity(1);
            }
            if (Objects.equals(str, "LB")) {
                lb.setOpacity(1);
            }
            if (Objects.equals(str, "DMF")) {
                dmf.setOpacity(1);
            }
            if (Objects.equals(str, "CMF")) {
                cmf.setOpacity(1);
            }
            if (Objects.equals(str, "LMF")) {
                lmf.setOpacity(1);
            }
            if (Objects.equals(str, "RMF")) {
                rmf.setOpacity(1);
            }
            if (Objects.equals(str, "AMF")) {
                amf.setOpacity(1);
            }
            if (Objects.equals(str, "SS")) {
                ss.setOpacity(1);
            }
            if (Objects.equals(str, "LWF")) {
                lwf.setOpacity(1);
            }
            if (Objects.equals(str, "RWF")) {
                rwf.setOpacity(1);
            }
            if (Objects.equals(str, "CF")) {
                cf.setOpacity(1);
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String id = FootballerOverview.id;
        try {
            name.setText(getName(id));
            club.setText(getClubName(id));
            coach.setText(getCoachName(id));
            foot.setText(getFoot(id));
            age.setText(getAge(id));
            dob.setText(getDob(id));
            height.setText(getHeight(id));
            weight.setText(getWeight(id));
            country.setText(getCountry(id));
            price.setText(getPrice(id));
            skill.setText(getSkills(id));
            setPosition(id);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
