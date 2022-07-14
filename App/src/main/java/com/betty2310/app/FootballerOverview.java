package com.betty2310.app;

import com.betty2310.app.connection.Database;
import com.betty2310.app.model.FootballerOverviewTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class FootballerOverview implements Initializable {
    public TableView<FootballerOverviewTable> table;
    public TableColumn<FootballerOverviewTable, String> colName;
    public TableColumn<FootballerOverviewTable, String> colID;
    public TableColumn<FootballerOverviewTable, String> colINation;
    public TableColumn<FootballerOverviewTable, Integer> colHeight;
    public TableColumn<FootballerOverviewTable, Integer> colIAge;
    public TableColumn<FootballerOverviewTable, Integer> colWeight;
    public TableColumn<FootballerOverviewTable, Integer> colPrice;
    public TableColumn<FootballerOverviewTable, String> colFoot;
    public TableColumn<FootballerOverviewTable, String> colClub;
    public TableColumn<FootballerOverviewTable, String> colCoach;
    public Label countRow;
    private Connection connection = new Database().connection();
    private ObservableList<FootballerOverviewTable> data;

    public static String id;

    public boolean checNullOrEmpty(String st) {
        return (!(st == null || st.isEmpty()));
    }

    public String handleQuery() {
        String query = "select * from footballer_overview where ";
        String condition = "name LIKE '%" + FootballerPane.name + "%'" +
                " and nationality LIKE '%" + FootballerPane.nation + "%'" +
                " and club LIKE '%" + FootballerPane.club + "%'" +
                " and coach LIKE '%" + FootballerPane.coach + "%'";
        if (checNullOrEmpty(FootballerPane.foot)) {
            condition += " and stronger_foot = '" + FootballerPane.foot + "'";
        }
        if (checNullOrEmpty(FootballerPane.age)) {
            condition += " and age >= " + FootballerPane.age;
        }
        if (checNullOrEmpty(FootballerPane.height)) {
            condition += " and height >= " + FootballerPane.height;
        }
        if (checNullOrEmpty(FootballerPane.weight)) {
            condition += " and weight >= " + FootballerPane.weight;
        }
        String conditionRating = "select footballer_id, name, nationality, height, weight, age, stronger_foot, price, club, coach from (" +
                query + condition +
                ") as \"fo\"" +
                " natural join footballer_rating order by " + FootballerPane.rating;
        String conditionRole = "select * from (select footballer_id from footballer_position where position = '" + FootballerPane.role + "') as \"fo\" natural join footballer_overview where " + condition;

        if (checNullOrEmpty(FootballerPane.rating) && !checNullOrEmpty(FootballerPane.role)) {
            return conditionRating;
        }
        if (!checNullOrEmpty(FootballerPane.rating) && checNullOrEmpty(FootballerPane.role)) {
            return conditionRole;
        }
        if (checNullOrEmpty(FootballerPane.role) && checNullOrEmpty(FootballerPane.rating)) {
            return "(" + conditionRating + ")" + " intersect " + "(" + conditionRole + ");";
        }
        return query + condition;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        data = FXCollections.observableArrayList();
        try {
            table.setPlaceholder(new Label("No rows to display"));

            ResultSet rs = connection.createStatement().executeQuery(handleQuery());

            while (rs.next()) {
                data.add(new FootballerOverviewTable(Integer.toString(rs.getInt("footballer_id")), rs.getString("name"), rs.getString("nationality"), rs.getInt("height"), rs.getInt("weight"), rs.getInt("age"), rs.getString("stronger_foot").charAt(0), rs.getInt("price"), rs.getString("club"), rs.getString("coach")));
            }

            colID.setCellValueFactory(new PropertyValueFactory<>("footballer_id"));
            colName.setCellValueFactory(new PropertyValueFactory<>("name"));
            colINation.setCellValueFactory(new PropertyValueFactory<>("nationality"));
            colHeight.setCellValueFactory(new PropertyValueFactory<>("height"));
            colWeight.setCellValueFactory(new PropertyValueFactory<>("weight"));
            colIAge.setCellValueFactory(new PropertyValueFactory<>("age"));
            colFoot.setCellValueFactory(new PropertyValueFactory<>("stronger_foot"));
            colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
            colClub.setCellValueFactory(new PropertyValueFactory<>("club"));
            colCoach.setCellValueFactory(new PropertyValueFactory<>("coach"));
            table.setItems(data);
            countRow.setText(String.valueOf(table.getItems().size()));

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void handleFootballer(MouseEvent mouseEvent) {
        if (mouseEvent.getClickCount() == 2 && !mouseEvent.isConsumed()) {
            mouseEvent.consume();
            FootballerOverviewTable rowData = table.getSelectionModel().getSelectedItem();
            if (rowData == null) return;
            id = rowData.getFootballer_id();
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FootballerDetail.fxml"));
                Parent window = fxmlLoader.load();
                Stage stage = new Stage();
                String title = rowData.getName() + " Footballer Detail";
                stage.setTitle(title);
                stage.setScene(new Scene(window));
                stage.show();
            } catch (IOException e) {
                System.out.println("Can't load Footballer Detail window.");
            }
        }
    }
}
