package com.betty2310.app;

import com.betty2310.app.connection.Database;
import io.github.palexdev.materialfx.controls.legacy.MFXLegacyComboBox;
import javafx.event.ActionEvent;
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
    public MFXLegacyComboBox listAbility;
    public MFXLegacyComboBox listRate;
    public Label valueAbility;
    public Label valueRate;
    public Label name1;
    public Label name2;
    public Label posRef;
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
            str = rs.getString("price") + "$";
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

    public String getTrophys(String id) throws SQLException {
        StringBuilder str = new StringBuilder();
        String query = "select  club_trophy.name from enrollment" +
                " join club_trophy on (" +
                " enrollment.club_id = club_trophy.club_id" +
                " and" +
                " enrollment.enrollment_start < club_trophy.date)" +
                " where footballer_id = " + id + ";";
        ResultSet rs = db.createStatement().executeQuery(query);
        while (rs.next()) {
            str.append(rs.getString("name")).append(", ");
        }
        return str.toString();
    }
    public String getClubs(String id) throws SQLException {
        StringBuilder str = new StringBuilder();
        String query = "SELECT DISTINCT league" +
                " FROM club_league" +
                " WHERE club_id IN (" +
                " SELECT club_id" +
                " FROM enrollment" +
                " WHERE footballer_id = " +
                id  +
                ");";
        ResultSet rs = db.createStatement().executeQuery(query);
        while (rs.next()) {
            str.append(rs.getString("league")).append(", ");
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

    public String getCoaches(String id) throws SQLException {
        StringBuilder str = new StringBuilder();
        String query = "WITH club_and_time(club_id) AS (\n" +
                "    SELECT club_id\n" +
                "    FROM enrollment\n" +
                "    WHERE footballer_id = " + id + "\n" +
                ")\n" +
                "SELECT coach.name\n" +
                "FROM club\n" +
                "         NATURAL JOIN club_and_time\n" +
                "         LEFT JOIN coach ON club.coach_id = coach.coach_id;";
        ResultSet rs = db.createStatement().executeQuery(query);
        while (rs.next()) {
            str.append(rs.getString("name")).append(", ");
        }
        return str.toString();

    }

    public String getLeagues(String id) throws SQLException {
        StringBuilder str = new StringBuilder();
        String query = "SELECT DISTINCT league\n" +
                "FROM club_league\n" +
                "WHERE club_id IN (\n" +
                "    SELECT club_id\n" +
                "    FROM enrollment\n" +
                "    WHERE footballer_id = " + id + "\n" +
                "    );";
        ResultSet rs = db.createStatement().executeQuery(query);
        while (rs.next()) {
            str.append(rs.getString("league")).append(", ");
        }
        return str.toString();

    }

    void setItemListRate() {
        listRate.getItems().add("as_gk");
        listRate.getItems().add("as_lb");
        listRate.getItems().add("as_rb");
        listRate.getItems().add("as_cb");
        listRate.getItems().add("as_dmf");
        listRate.getItems().add("as_lmf");
        listRate.getItems().add("as_rmf");
        listRate.getItems().add("as_cmf");
        listRate.getItems().add("as_amf");
        listRate.getItems().add("as_lwf");
        listRate.getItems().add("as_rwf");
        listRate.getItems().add("as_ss");
        listRate.getItems().add("as_cf");
        listRate.getItems().add("");
    }

    void setItemsListAbility() {
        listAbility.getItems().add("offensive_awareness");
        listAbility.getItems().add("ball_control");
        listAbility.getItems().add("dribbling");
        listAbility.getItems().add("tight_possession");
        listAbility.getItems().add("low_pass");
        listAbility.getItems().add("lofted_pass");
        listAbility.getItems().add("finishing");
        listAbility.getItems().add("heading");
        listAbility.getItems().add("set_piece_taking");
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
        listAbility.getItems().add("defensive_engagement");
        listAbility.getItems().add("gk_awareness");
        listAbility.getItems().add("gk_catching");
        listAbility.getItems().add("gk_parrying");
        listAbility.getItems().add("gk_reflexes");
        listAbility.getItems().add("gk_reach");
        listAbility.getItems().add("weak_foot_usage");
        listAbility.getItems().add("weak_foot_accuracy");
        listAbility.getItems().add("form");
        listAbility.getItems().add("injury_resistance");
    }

    void setTextPositionRef() {
        String st = "1. GK: goal keeper\n" +
                "2. CB: centre back\n" +
                "3. RB: right back\n" +
                "4. LB: left back\n" +
                "5. DMF: defensive mid fielder\n" +
                "6. CMF: centre mid fielder\n" +
                "7. AMF: attacking mid fielder\n" +
                "8. LMF: left winger forward\n" +
                "9. RMF: right winger forward\n" +
                "10. SS: second striker\n" +
                "11. LWF: left winger forward\n" +
                "12. RWF: right winger forward\n" +
                "13. CF: centre forward\n";
        posRef.setText(st);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String id = FootballerOverview.id;
        setItemListRate();
        setItemsListAbility();
        setTextPositionRef();
        try {
            name.setText(getName(id));
            name1.setText(getName(id));
            name2.setText(getName(id));
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
            coachs.setText(getCoaches(id));
            leagues.setText(getLeagues(id));
            trophys.setText(getTrophys(id));
            clubs.setText(getClubs(id));
            setPosition(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void calRate(ActionEvent event) throws SQLException {
        String st = (String) listRate.getValue();
        ResultSet rs = db.createStatement().executeQuery("select " + st + " from footballer_rating where footballer_id = " + id + ";");
        while (rs.next()) {
            valueRate.setText(rs.getString(st));
        }
    }

    public void calAbility(ActionEvent event) throws SQLException {
        String st = (String) listAbility.getValue();
        ResultSet rs = db.createStatement().executeQuery("select " + st + " from footballer_ability where footballer_id = " + id + ";");
        while (rs.next()) {
            valueAbility.setText(rs.getString(st));
        }
    }
}
