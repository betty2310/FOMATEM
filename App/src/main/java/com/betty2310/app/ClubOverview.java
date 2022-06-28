package com.betty2310.app;

import com.betty2310.app.connection.Database;
import com.betty2310.app.table.ClubOverviewTable;
import com.betty2310.app.table.FootballerOverviewTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
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
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ClubOverview implements Initializable {
    public TableView<ClubOverviewTable> table;
    @FXML
    private TableColumn<ClubOverviewTable, String> colCountry;

    @FXML
    private TableColumn<ClubOverviewTable, Integer> colID;

    @FXML
    private TableColumn<ClubOverviewTable, String> colName;

    private ObservableList<ClubOverviewTable> data;

    @FXML
    void handleClubDetail(MouseEvent event) {
        if (event.getClickCount() == 2 && !event.isConsumed()) {
            event.consume();

            ClubOverviewTable rowData = table.getSelectionModel().getSelectedItem();
            if (rowData == null) return;
            Label text = new Label("You click on student id: " +rowData.getClub_id() );
            text.setFont(new Font("Monaco", 20));
            Pane pane = new Pane();
            pane.getChildren().add(text);
            Stage stage = new Stage();
            stage.setScene(new Scene(pane));
            stage.setTitle("Footballer ID " + rowData.getClub_id() + " Detail");
            stage.show();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        data = FXCollections.observableArrayList();
        try {
            Database db = new Database();
            Connection connection = db.connection();

            table.setPlaceholder(new Label("No rows to display"));

            ResultSet rs = connection.createStatement().executeQuery("SELECT club_id, name,country FROM club LIMIT 100;");

            while (rs.next()) {
                data.add(new ClubOverviewTable(Integer.toString(rs.getInt("club_id")), rs.getString("name"), rs.getString("country")));
            }

            colID.setCellValueFactory(new PropertyValueFactory<>("club_id"));
            colName.setCellValueFactory(new PropertyValueFactory<>("name"));
            colCountry.setCellValueFactory(new PropertyValueFactory<>("country"));
            table.setItems(data);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}