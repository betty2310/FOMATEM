package com.betty2310.app;

import com.betty2310.app.connection.Database;
import com.betty2310.app.model.FootballerOverviewTable;
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

    private ObservableList<FootballerOverviewTable> data;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        data = FXCollections.observableArrayList();
        try {
            Database db = new Database();
            Connection connection = db.connection();

            table.setPlaceholder(new Label("No rows to display"));

            ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM footballer_overview;");

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

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void handleFootballer(MouseEvent mouseEvent) {
        if (mouseEvent.getClickCount() == 2 && !mouseEvent.isConsumed()) {
            mouseEvent.consume();

            FootballerOverviewTable rowData = table.getSelectionModel().getSelectedItem();
            if (rowData == null) return;
            Label text = new Label("You click on student id: " +rowData.getFootballer_id() );
            text.setFont(new Font("Monaco", 20));
            Pane pane = new Pane();
            pane.getChildren().add(text);
            Stage stage = new Stage();
            stage.setScene(new Scene(pane));
            stage.setTitle("Footballer ID " + rowData.getFootballer_id() + " Detail");
            stage.show();
        }
    }
}
