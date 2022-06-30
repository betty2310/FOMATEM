package com.betty2310.app;

import com.betty2310.app.connection.Database;
import com.betty2310.app.model.CoachOverviewTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.net.URL;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CoachOverview implements Initializable {
    public TableView<CoachOverviewTable> table;
    public TableColumn<CoachOverviewTable, Integer> colID;
    public TableColumn<CoachOverviewTable, String> colName;

    public TableColumn<CoachOverviewTable, String> colClub;
    public TableColumn<CoachOverviewTable, String> colTrophy;

    public ObservableList<CoachOverviewTable> data;
    public Label countRow;

    public void handleCoachDetail(MouseEvent mouseEvent) {
        if (mouseEvent.getClickCount() == 2 && !mouseEvent.isConsumed()) {
            mouseEvent.consume();

            CoachOverviewTable rowData = table.getSelectionModel().getSelectedItem();
            if (rowData == null) return;
            Label text = new Label("You click on student id: " +rowData.getCoach_id() );
            text.setFont(new Font("Monaco", 20));
            Pane pane = new Pane();
            pane.getChildren().add(text);
            Stage stage = new Stage();
            stage.setScene(new Scene(pane));
            stage.setTitle("Coach ID " + rowData.getCoach_id() + " Detail");
            stage.show();
        }
    }

    public String handleQuery() {
        String query =  "SELECT * FROM coach_overview"
                + " WHERE coachname LIKE '%" + CoachPane.coachName + "%'"
                + " AND club LIKE '%" + CoachPane.coachClub + "%'"
                + ";";
        return query;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        data = FXCollections.observableArrayList();
        try {
            Database db = new Database();
            Connection connection = db.connection();

            table.setPlaceholder(new Label("No rows to display"));

            ResultSet rs = connection.createStatement().executeQuery(handleQuery());

            while (rs.next()) {
                data.add(new CoachOverviewTable(rs.getInt("coach_id"), rs.getString("coachname"), rs.getString("club"), rs.getInt("totaltrophy")));
            }

            colID.setCellValueFactory(new PropertyValueFactory<>("coach_id"));
            colName.setCellValueFactory(new PropertyValueFactory<>("coachname"));
            colClub.setCellValueFactory(new PropertyValueFactory<>("club"));
            colTrophy.setCellValueFactory(new PropertyValueFactory<>("totaltrophy"));
            table.setItems(data);
            countRow.setText(String.valueOf(table.getItems().size()));

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
