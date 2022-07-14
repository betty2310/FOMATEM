package com.betty2310.app;

import com.betty2310.app.connection.Database;
import com.betty2310.app.model.ClubOverviewTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

public class ClubOverview implements Initializable {
    public TableView<ClubOverviewTable> table;
    public Label countRow;

    public static String clubID;
    @FXML
    private TableColumn<ClubOverviewTable, String> colCountry;

    @FXML
    private TableColumn<ClubOverviewTable, Integer> colID;

    @FXML
    private TableColumn<ClubOverviewTable, String> colName;

    public TableColumn<ClubOverviewTable, String> colCoachName;
    public TableColumn<ClubOverviewTable, String> colNumberOfFootballer;
    public TableColumn<ClubOverviewTable, Spring> colNumberOfTrophy;

    private ObservableList<ClubOverviewTable> data;


    @FXML
    void handleClubDetail(MouseEvent event) {
        if (event.getClickCount() == 2 && !event.isConsumed()) {
            event.consume();
            ClubOverviewTable rowData = table.getSelectionModel().getSelectedItem();
            if (rowData == null) return;
            clubID = rowData.getClub_id();
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ClubDetail.fxml"));
                Parent window = fxmlLoader.load();
                Stage stage = new Stage();
                String title = rowData.getClubName() + " Club";
                stage.setTitle(title);
                stage.setScene(new Scene(window));
                stage.show();
            } catch (IOException e) {
                System.out.println("Can't load Club Detail window.");
            }
        }

    }

    public String handleQuery() {
        String query = "(select * from club_overview where club_name LIKE '%" + ClubPane.clubName + "%')" +
                " intersect " +
                "(select * from club_overview where club_country LIKE '%" + ClubPane.countryName + "%')" +
                " intersect " +
                "(select * from club_overview where coach_name LIKE '%" + ClubPane.coachName + "%')";
        if (!(ClubPane.leagueName == null || ClubPane.leagueName.isEmpty())) {
            query += " intersect " +
                    "(select * from club_overview where club_id in (select club_id from club_league where league = '" + ClubPane.leagueName + "'))";
        }
        if (ClubPane.trophyName == null || ClubPane.trophyName.isEmpty()) {
            query += ";";
        }
        else {
            query +=" intersect " +
                    "(select * from club_overview where club_id in (select club_id from club_trophy where name = '" + ClubPane.trophyName + "'));";

        }
        return query;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        data = FXCollections.observableArrayList();
        try {
            Connection connection = new Database().connection();
            table.setPlaceholder(new Label("No rows to display"));
            ResultSet rs = connection.createStatement().executeQuery(handleQuery());

            while (rs.next()) {
                data.add(new ClubOverviewTable(Integer.toString(rs.getInt("club_id")), rs.getString("club_name"), rs.getString("club_country"), rs.getString("coach_name"), Integer.toString(rs.getInt("number_of_footballer")), Integer.toString(rs.getInt("number_of_trophy"))));
            }

            colID.setCellValueFactory(new PropertyValueFactory<>("club_id"));
            colName.setCellValueFactory(new PropertyValueFactory<>("clubName"));
            colCountry.setCellValueFactory(new PropertyValueFactory<>("clubCountry"));
            colCoachName.setCellValueFactory(new PropertyValueFactory<>("coachName"));
            colNumberOfFootballer.setCellValueFactory(new PropertyValueFactory<>("nFootballer"));
            colNumberOfTrophy.setCellValueFactory(new PropertyValueFactory<>("nTrophy"));
            table.setItems(data);
            countRow.setText(String.valueOf(table.getItems().size()));

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}